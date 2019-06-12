package com.github.febrity.decathlontestappback.controller;

import com.github.febrity.decathlontestappback.entity.Geosearch;
import com.github.febrity.decathlontestappback.entity.WikiResponse;
import com.github.febrity.decathlontestappback.services.WikiPlacesViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class WikiPlacesViewerController {

    private final WikiPlacesViewerService wikiPlacesViewerService;

    @GetMapping(value = "/places")
    public ResponseEntity<WikiResponse> getWikiPlaces(@RequestParam Double lat, @RequestParam Double lon) {
        return wikiPlacesViewerService.getWikiArticles(lat, lon)
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/articles")
    public ResponseEntity<List<String>> getArticles(@RequestParam(required = false) Double lat, @RequestParam(required = false) Double lon) {
        return wikiPlacesViewerService.getWikiArticles(lat, lon)
                .map(response ->
                        ResponseEntity.ok()
                                .body(response
                                        .getQuery()
                                        .getGeosearch()
                                        .stream()
                                        .map(Geosearch::getTitle)
                                        .collect(Collectors.toList())))
                .orElse(ResponseEntity.notFound().build());
    }
}
