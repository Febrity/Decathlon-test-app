package com.github.febrity.decathlontestappback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Geosearch {

    private Integer pageid;
    private Integer ns;
    private String title;
    private Double lat;
    private Double lon;
    private Integer dist;
    private String primary;
    private Map<String, Object> additionalProperties = new HashMap<>();
}