package com.example.demo.service.impl;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsAggregate;
import com.example.demo.dto.request.SearchDTO;
import com.example.demo.dto.response.*;
import com.example.demo.service.ISearchService;
import com.example.demo.util.Constants;
import com.example.demo.util.NativeQueryBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.Aggregation;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregation;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.demo.util.Constants.Business.OFFERINGS_AGGREGATE_NAME;

@Service
public class SearchService implements ISearchService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SearchService.class);

    private final ElasticsearchOperations elasticsearchOperations;

    public SearchService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public SearchResponseDTO search(SearchDTO parameters) {
        log.info("search request: {}", parameters);
        NativeQuery query = NativeQueryBuilder.toSearchQuery(parameters);
        log.info("bool query: {}", query.getQuery());
        SearchHits<BusinessDocument> searchHits = this.elasticsearchOperations.search(query, BusinessDocument.class, Constants.Index.BUSINESS);
        return buildResponse(parameters, searchHits);
    }

    public SearchResponseDTO buildResponse(SearchDTO parameters, SearchHits<BusinessDocument> searchHits) {
        List<BusinessDocument> results = searchHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .toList();
        SearchPage<BusinessDocument> searchPage = SearchHitSupport.searchPageFor(searchHits, PageRequest.of(parameters.getPage(), parameters.getSize()));
        PaginationDTO pagination = new PaginationDTO(
                searchPage.getNumber(),
                searchPage.getNumberOfElements(),
                searchPage.getTotalElements(),
                searchPage.getTotalPages()
        );
        List<FacetDTO> facets = buildFacets((List<ElasticsearchAggregation>) searchHits.getAggregations().aggregations());
        return new SearchResponseDTO(results,
                facets,
                pagination,
                searchHits.getExecutionDuration().toMillis());
    }

    public List<FacetDTO> buildFacets(List<ElasticsearchAggregation> aggregations) {
        Map<String, Aggregate> map = aggregations.stream()
                .map(ElasticsearchAggregation::aggregation)
                .collect(Collectors.toMap(
                        Aggregation::getName,
                        Aggregation::getAggregate
                ));
        return List.of(buildFacet(OFFERINGS_AGGREGATE_NAME, map.get(OFFERINGS_AGGREGATE_NAME).sterms())
        );
    }

    public FacetDTO buildFacet(String name, StringTermsAggregate stringTermsAggregate) {
        List<FacetItemDTO> facetItems = stringTermsAggregate.buckets()
                .array()
                .stream()
                .map(b -> new FacetItemDTO(b.key().stringValue(), b.docCount()))
                .toList();
        return new FacetDTO(name, facetItems);
    }

}
