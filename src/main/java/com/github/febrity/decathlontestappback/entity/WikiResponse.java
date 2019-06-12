package com.github.febrity.decathlontestappback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WikiResponse {
    private String batchcomplete;
    private Query query;
    private Map<String, Object> additionalProperties = new HashMap<>();
}