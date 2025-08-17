package com.example.demo.dto.request;

import com.example.demo.exception.BadRequestException;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class SearchDTO {

    private String query;
    private String distance;
    private Double latitude;
    private Double longitude;
    private Double rating;
    private String state;
    private String offerings;
    private Integer page;
    private Integer size;

    public SearchDTO(String query,
                         String distance,
                         Double latitude,
                         Double longitude,
                         Double rating,
                         String state,
                         String offerings,
                         Integer page,
                         Integer size) {
        if (!StringUtils.hasText(query)) {
            throw new BadRequestException("query can not be empty");
        }
        this.query = query;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.state = state;
        this.offerings = offerings;
        this.page = Objects.requireNonNullElse(page, 0);
        this.size = Objects.requireNonNullElse(size, 10);
    }

    public String getQuery() {
        return query;
    }

    public String getDistance() {
        return distance;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getRating() {
        return rating;
    }

    public String getState() {
        return state;
    }

    public String getOfferings() {
        return offerings;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public void setQuery(String query) {
        if (!StringUtils.hasText(query)) {
            throw new BadRequestException("query can not be empty");
        }
        this.query = query;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setOfferings(String offerings) {
        this.offerings = offerings;
    }

    public void setPage(Integer page) {
        this.page = Objects.requireNonNullElse(page, 0);
    }

    public void setSize(Integer size) {
        this.size = Objects.requireNonNullElse(size, 10);
    }

    // equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchDTO that)) return false;
        return Objects.equals(query, that.query) &&
                Objects.equals(distance, that.distance) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(state, that.state) &&
                Objects.equals(offerings, that.offerings) &&
                Objects.equals(page, that.page) &&
                Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, distance, latitude, longitude, rating, state, offerings, page, size);
    }

    // toString
    @Override
    public String toString() {
        return "SearchRequest{" +
                "query='" + query + '\'' +
                ", distance='" + distance + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", rating=" + rating +
                ", state='" + state + '\'' +
                ", offerings='" + offerings + '\'' +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}

