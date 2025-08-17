package com.example.demo.service;

import com.example.demo.dto.request.SuggestionDTO;

import java.util.List;

public interface ISuggestionService {
    List<String> fetchSuggestions(SuggestionDTO parameters);
}
