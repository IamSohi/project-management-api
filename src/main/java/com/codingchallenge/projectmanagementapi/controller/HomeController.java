package com.codingchallenge.projectmanagementapi.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> index() {
        String html = """
            <html>
              <head><title>Project Management API</title></head>
              <body>
                <h1>Welcome to the Project Management API</h1>
                <p>Refer to the <a href="https://github.com/IamSohi/project-management-api/blob/main/README.md">API documentation</a> for usage details.</p>
              </body>
            </html>
        """;
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html);
    }
}