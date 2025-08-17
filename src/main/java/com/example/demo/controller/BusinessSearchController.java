package com.example.demo.controller;

import com.example.demo.dto.request.SearchDTO;
import com.example.demo.dto.request.SuggestionDTO;
import com.example.demo.dto.response.SearchResponseDTO;
import com.example.demo.service.impl.SearchService;
import com.example.demo.service.impl.SuggestionService;
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
    public List<String> suggest(SuggestionDTO parameters){
        return suggestionService.fetchSuggestions(parameters);
    }

    @GetMapping("/search")
    public SearchResponseDTO search(SearchDTO parameters){
        return searchService.search(parameters);
    }
}
