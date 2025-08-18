package com.example.demo.util;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.example.demo.dto.request.SearchDTO;
import com.example.demo.dto.request.SuggestionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;

import java.util.List;
import java.util.Optional;

public class NativeQueryBuilder {
    private static final List<QueryRule> FILTER_QUERY_RULES = List.of(
            QueryRules.STATE_QUERY,
            QueryRules.RATING_QUERY,
            QueryRules.DISTANCE_QUERY,
            QueryRules.OFFERINGS_QUERY
    );

    private static final List<QueryRule> MUST_QUERY_RULES = List.of(
            QueryRules.SEARCH_QUERY
    );

    private static final List<QueryRule> SHOULD_QUERY_RULES = List.of(
            QueryRules.CATEGORY_QUERY
    );

    public static NativeQuery toSuggestQuery(SuggestionDTO suggestionRequest) {
        var suggester = ElasticsearchUtil.buildCompletionSuggester(
                Constants.Suggestion.SUGGEST_NAME,
                Constants.Suggestion.SEARCH_TERM,
                suggestionRequest.getPrefix(),
                suggestionRequest.getLimit()
        );
        return NativeQuery.builder()
                .withSuggester(suggester)
                .withMaxResults(0) // We do not want any results object
                .withSourceFilter(FetchSourceFilter.of(b -> b.withExcludes("*"))) // disable fetching the source object
                .build();
    }

    public static NativeQuery toSearchQuery(SearchDTO parameters) {
        var filterQueries = buildQueries(FILTER_QUERY_RULES, parameters);
        var mustQueries = buildQueries(MUST_QUERY_RULES, parameters).stream()
                .map(NativeQueryBuilder::fixBoostDecimalSeparator)
                .toList();
        var shouldQueries = buildQueries(SHOULD_QUERY_RULES, parameters).stream()
                .map(NativeQueryBuilder::fixBoostDecimalSeparator)
                .toList();

        var boolQuery = BoolQuery.of(builder -> builder
                .filter(filterQueries)
                .must(mustQueries)
                .should(shouldQueries));

        return NativeQuery.builder()
                .withQuery(Query.of(builder -> builder.bool(boolQuery)))
                .withAggregation(Constants.Business.OFFERINGS_AGGREGATE_NAME,
                        ElasticsearchUtil.buildTermsAggregation(Constants.Business.OFFERINGS_RAW))
                .withPageable(PageRequest.of(parameters.getPage(), parameters.getSize()))
                .withTrackTotalHits(true)
                .build();
    }

    private static Query fixBoostDecimalSeparator(Query query) {
        try {
            String json = new ObjectMapper().writeValueAsString(query);
            json = json.replaceAll("(\\^\\d+),(\\d+)", "$1.$2");
            return new ObjectMapper().readValue(json, Query.class);
        } catch (Exception e) {
            return query;
        }
    }


    private static List<Query> buildQueries(List<QueryRule> queryRules, SearchDTO parameters) {
        return queryRules.stream()
                .map(qr -> qr.build(parameters))
                .flatMap(Optional::stream)
                .toList();
    }
}
