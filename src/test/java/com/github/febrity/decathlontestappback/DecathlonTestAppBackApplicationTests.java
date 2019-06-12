package com.github.febrity.decathlontestappback;

import com.github.febrity.decathlontestappback.controller.WikiPlacesViewerController;
import com.github.febrity.decathlontestappback.entity.WikiResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DecathlonTestAppBackApplicationTests {

    @Autowired
    private WikiPlacesViewerController controller;

    @Test
    public void contextLoads() {

        ResponseEntity<WikiResponse> responseWkiArticle = controller.getWikiPlaces(55.86521530151367, 37.60358428955078);
        assertEquals(responseWkiArticle.getStatusCode(), HttpStatus.OK);

        ResponseEntity<List<String>> responseWkiTitle = controller.getArticles(55.86521530151367, 37.60358428955078);
        assertEquals(responseWkiTitle.getStatusCode(), HttpStatus.OK);
    }
}
