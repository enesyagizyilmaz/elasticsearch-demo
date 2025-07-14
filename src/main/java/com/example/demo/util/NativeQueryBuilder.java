package com.example.demo.util;

import com.example.demo.dto.request.SuggestionRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;

public class NativeQueryBuilder {
    public static NativeQuery toSuggestQuery(SuggestionRequest suggestionRequest) {
        var suggester = ElasticsearchUtil.buildCompletionSuggester(
                Constants.Suggestion.SUGGEST_NAME,
                Constants.Suggestion.SEARCH_TERM,
                suggestionRequest.prefix(),
                suggestionRequest.limit()
        );
        return NativeQuery.builder()
                .withSuggester(suggester)
                .withMaxResults(0) // We do not want any results object
                .withSourceFilter(FetchSourceFilter.of(b -> b.withExcludes("*"))) // disable fetching the source object
                .build();
    }
}
