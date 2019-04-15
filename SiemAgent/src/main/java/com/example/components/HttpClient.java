package com.example.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
public class HttpClient implements CommandLineRunner {

    @Autowired
    private RestTemplate template;

    @Override
    public void run(String... args) throws Exception {
        try {
            ResponseEntity<String> response = template.getForEntity("https://localhost:8443/test",
                    String.class);
            System.out.println(response.getBody());
        } catch(Exception e) {
            System.out.println("Bad certificate!!!!!!!!");
        }
    }
}