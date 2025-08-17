package com.example.demo.dto.response;

public class FacetItemDTO {

    private String key;
    private long count;

    public FacetItemDTO() {
    }

    public FacetItemDTO(String key, long count) {
        this.key = key;
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}

