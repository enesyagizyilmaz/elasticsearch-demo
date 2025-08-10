package com.example.demo.dto.response;

import java.util.List;

public record Facet(String name,
                    List<FacetItem> items) {
}
