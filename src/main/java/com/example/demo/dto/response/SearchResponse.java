package com.example.demo.dto.response;

import com.example.demo.util.Constants;

import java.util.List;

public record SearchResponse(List<Constants.Business> results,
                             List<Facet> facets,
                             Pagination pagination,
                             long timeTaken) {
}
