package com.example.demo.dto.request;

import com.example.demo.exception.BadRequestException;
import org.springframework.util.StringUtils;

import java.util.Objects;

public record SearchRequest(String query,
                            String distance,
                            Double latitude,
                            Double longitude,
                            Double rating,
                            String state,
                            String offerings,
                            Integer page,  // 0 indexed
                            Integer size){

    public SearchRequest {
        if(!StringUtils.hasText(query)){
            throw new BadRequestException("query can not be empty");
        }
        page = Objects.requireNonNullElse(page, 0);
        size = Objects.requireNonNullElse(size, 10);
    }
}
