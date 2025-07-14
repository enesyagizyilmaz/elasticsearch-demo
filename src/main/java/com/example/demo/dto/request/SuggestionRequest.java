package com.example.demo.dto.request;

import com.example.demo.exception.BadRequestException;
import org.springframework.util.StringUtils;

import java.util.Objects;

public record SuggestionRequest(String prefix, Integer limit) {
    public SuggestionRequest {
        if(!StringUtils.hasText(prefix)){
            throw new BadRequestException("prefix can not be empty");
        }
        limit = Objects.requireNonNullElse(limit, 10);
    }
}
