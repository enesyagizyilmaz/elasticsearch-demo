package com.example.demo.dto.response;

import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

public class BusinessDocument {
    private String name;
    private String address;
    private String description;
    private String state;
    List<String> category;
    private List<String> offerings;
    private String url;
    @Field(name = "avg_rating")
    private Float rating;
    @Field(name = "num_of_reviews")
    Integer reviewsCount;

    public BusinessDocument() {
    }

    public BusinessDocument(String name, String address, String description, String state, List<String> category, List<String> offerings, Double avgRating, String url, Float rating, Integer reviewsCount) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.state = state;
        this.category = category;
        this.offerings = offerings;
        this.url = url;
        this.rating = rating;
        this.reviewsCount = reviewsCount;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getOfferings() {
        return offerings;
    }

    public void setOfferings(List<String> offerings) {
        this.offerings = offerings;
    }
}
