package com.example.demo.controller;

import com.example.demo.dto.request.SearchRequest;
import com.example.demo.dto.request.SuggestionRequest;
import com.example.demo.dto.response.SearchResponse;
import com.example.demo.service.SearchService;
import com.example.demo.service.SuggestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BusinessSearchController {
    private final SearchService searchService;
    private final SuggestionService suggestionService;

    public BusinessSearchController(SearchService searchService, SuggestionService suggestionService) {
        this.searchService = searchService;
        this.suggestionService = suggestionService;
    }

    @GetMapping("/suggestions")
    public List<String> suggest(SuggestionRequest parameters){
        return this.suggestionService.fetchSuggestions(parameters);
    }

    @GetMapping("/search")
    public SearchResponse search(SearchRequest parameters){
        return this.searchService.search(parameters);
    }
}
