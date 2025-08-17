package com.example.demo.dto.request;

import com.example.demo.exception.BadRequestException;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class SuggestionDTO {
    private String prefix;
    private Integer limit;

    public SuggestionDTO(String prefix, Integer limit) {
        if (!StringUtils.hasText(prefix)) {
            throw new BadRequestException("prefix can not be empty");
        }
        this.prefix = prefix;
        this.limit = Objects.requireNonNullElse(limit, 10);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        if (!StringUtils.hasText(prefix)) {
            throw new BadRequestException("prefix can not be empty");
        }
        this.prefix = prefix;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = Objects.requireNonNullElse(limit, 10);
    }
}

