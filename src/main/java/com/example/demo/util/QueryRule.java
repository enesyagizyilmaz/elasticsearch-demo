package com.example.demo.util;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.example.demo.dto.request.SearchRequest;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public record QueryRule(Predicate<SearchRequest> predicate,
                        Function<SearchRequest, Query> function) {
    public static QueryRule of(Predicate<SearchRequest> predicate, Function<SearchRequest, Query> function) {
        return new QueryRule(predicate, function);
    }
    public Optional<Query> build(SearchRequest parameters) {
        return Optional.of(parameters)
                .filter(this.predicate())
                .map(this.function());
    }
}
