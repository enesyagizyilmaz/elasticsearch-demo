package com.example.demo.util;

import java.util.List;
import java.util.Objects;
import java.util.Locale;
import java.util.stream.Stream;

import static com.example.demo.util.Constants.Business.*;
import static com.example.demo.util.ElasticsearchUtil.*;

public class QueryRules {
    private static final String BOOST_FIELD_FORMAT = "%s^%f";

    public static final QueryRule STATE_QUERY = QueryRule.of(
            srp -> Objects.nonNull(srp.getState()),
            srp -> buildTermQuery(STATE, srp.getState(), 1.0f)
    );

    public static final QueryRule OFFERINGS_QUERY = QueryRule.of(
            srp -> Objects.nonNull(srp.getOfferings()),
            srp -> buildTermQuery(OFFERINGS_RAW, srp.getOfferings(), 1.0f)
    );

    public static final QueryRule RATING_QUERY = QueryRule.of(
            srp -> Objects.nonNull(srp.getRating()),
            srp -> buildRangeQuery(RATING, builder -> builder.gte(srp.getRating()))
    );

    public static final QueryRule DISTANCE_QUERY = QueryRule.of(
            srp -> Stream.of(srp.getDistance(), srp.getLongitude(), srp.getLatitude()).allMatch(Objects::nonNull),
            srp -> buildGeoDistanceQuery(LOCATION, srp.getDistance(), srp.getLatitude(), srp.getLongitude())
    );

    public static final QueryRule CATEGORY_QUERY = QueryRule.of(
            srp -> Objects.nonNull(srp.getQuery()),  // can also use Predicates.isTrue() if it is true always
            srp -> buildTermQuery(CATEGORY_RAW, srp.getQuery(), 5.0f)
    );

    private static final List<String> SEARCH_BOOST_FIELDS = List.of(
            boostField(NAME, 2.0f),
            boostField(CATEGORY, 1.5f),
            boostField(OFFERINGS, 1.5f),
            boostField(ADDRESS, 1.2f),
            boostField(DESCRIPTION, 0.5f)
    );

    public static final QueryRule SEARCH_QUERY = QueryRule.of(
            srp -> Objects.nonNull(srp.getQuery()),  // can also use Predicates.isTrue() if it is true always
            srp -> buildMultiMatchQuery(SEARCH_BOOST_FIELDS, srp.getQuery())
    );

    private static String boostField(String field, float boost) {
        return String.format(Locale.US, BOOST_FIELD_FORMAT, field, boost);
    }
}
