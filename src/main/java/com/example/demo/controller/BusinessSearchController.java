package com.example.demo.controller;

import com.example.demo.dto.request.SuggestionRequest;
import com.example.demo.service.SuggestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BusinessSearchController {
    private final SuggestionService suggestionService;

    public BusinessSearchController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping("/suggestions")
    public List<String> suggest(SuggestionRequest suggestionRequest){
        return suggestionService.fetchSuggestions(suggestionRequest);
    }
}
