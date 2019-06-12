package com.github.febrity.decathlontestappback.services;

import com.github.febrity.decathlontestappback.entity.WikiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class WikiPlacesViewerService {

    @Value("${base.url}")
    private String baseUrl;

    @Value("${default.coordinates.latitude}")
    private String defaultLatitude;

    @Value("${default.coordinates.longitude}")
    private String defaultLongitude;

    public Optional<WikiResponse> getWikiArticles(Double lat, Double lon) {

        Map<String, String> uriVariables = new HashMap<>();

        uriVariables.put("radius", "10000");
        uriVariables.put("limit", "50");

        if(lat == null || lon == null || lat.isNaN() || lon.isNaN()){
            log.info("Using default value for lat: " + defaultLatitude + ", lon: " + defaultLongitude);
            uriVariables.put("lat", defaultLatitude);
            uriVariables.put("lon", defaultLongitude);
        } else {
            uriVariables.put("lat", lat.toString());
            uriVariables.put("lon", lon.toString());
        }

        RestTemplate restTemplate = new RestTemplate();
        String wikiPlacesUrl = baseUrl + "?action=query&list=geosearch&gsradius={radius}&gscoord={lat}|{lon}&gslimit={limit}&format=json";

        try {
            WikiResponse response = restTemplate.getForObject(wikiPlacesUrl, WikiResponse.class, uriVariables);
            if(response != null){
                log.info("Success mapping to object " + response.getClass());
                return Optional.of(response);
            } else {
                log.info("Object from url:" + wikiPlacesUrl);
                return Optional.empty();
            }
        } catch (RestClientException restException) {
            log.info("Url is not defined with exception " + restException);
            return Optional.empty();
        }
    }
}
