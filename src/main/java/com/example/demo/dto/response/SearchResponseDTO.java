package com.example.demo.dto.response;

import java.util.List;

public class SearchResponseDTO {
    private List<BusinessDocument> results;
    private List<FacetDTO> facets;
    private PaginationDTO pagination;
    private long timeTaken;

    public SearchResponseDTO() {
    }

    public SearchResponseDTO(List<BusinessDocument> results,
                             List<FacetDTO> facets,
                             PaginationDTO pagination,
                             long timeTaken) {
        this.results = results;
        this.facets = facets;
        this.pagination = pagination;
        this.timeTaken = timeTaken;
    }

    public List<BusinessDocument> getResults() {
        return results;
    }

    public void setResults(List<BusinessDocument> results) {
        this.results = results;
    }

    public List<FacetDTO> getFacets() {
        return facets;
    }

    public void setFacets(List<FacetDTO> facets) {
        this.facets = facets;
    }

    public PaginationDTO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }
}

