package com.github.febrity.decathlontestappback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Query {

    private List<Geosearch> geosearch = null;
    private Map<String, Object> additionalProperties = new HashMap<>();
}