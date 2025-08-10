package com.example.demo.dto.response;

import java.util.List;

public record SearchResponse(List<BusinessDocument> results,
                             List<Facet> facets,
                             Pagination pagination,
                             long timeTaken) {
}
