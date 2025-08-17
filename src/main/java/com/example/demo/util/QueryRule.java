package com.example.demo.util;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.example.demo.dto.request.SearchDTO;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public record QueryRule(Predicate<SearchDTO> predicate,
                        Function<SearchDTO, Query> function) {
    public static QueryRule of(Predicate<SearchDTO> predicate, Function<SearchDTO, Query> function) {
        return new QueryRule(predicate, function);
    }
    public Optional<Query> build(SearchDTO parameters) {
        return Optional.of(parameters)
                .filter(this.predicate())
                .map(this.function());
    }
}
