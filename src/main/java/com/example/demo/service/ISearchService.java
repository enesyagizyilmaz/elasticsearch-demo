package com.example.demo.service;

import co.elastic.clients.elasticsearch._types.aggregations.StringTermsAggregate;
import com.example.demo.dto.request.SearchDTO;
import com.example.demo.dto.response.BusinessDocument;
import com.example.demo.dto.response.FacetDTO;
import com.example.demo.dto.response.SearchResponseDTO;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregation;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

public interface ISearchService {
    SearchResponseDTO search(SearchDTO parameters);
    SearchResponseDTO buildResponse(SearchDTO parameters, SearchHits<BusinessDocument> searchHits);
    List<FacetDTO> buildFacets(List<ElasticsearchAggregation> aggregations);
    FacetDTO buildFacet(String name, StringTermsAggregate stringTermsAggregate);
}
