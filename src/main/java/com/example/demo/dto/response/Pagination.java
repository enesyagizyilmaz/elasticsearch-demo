package com.example.demo.dto.response;

public record Pagination(int page,
                         int size,
                         long totalElements,
                         int totalPages) {
}
