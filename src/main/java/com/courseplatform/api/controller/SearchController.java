package com.courseplatform.api.controller;

import com.courseplatform.api.dto.SearchResultDto;
import com.courseplatform.api.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public List<SearchResultDto> search(@RequestParam String q) {
        return searchService.search(q);
    }
}

