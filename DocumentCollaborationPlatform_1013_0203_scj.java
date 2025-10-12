// 代码生成时间: 2025-10-13 02:03:19
// DocumentCollaborationPlatform.java

package com.example.collaborationplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
public class DocumentCollaborationPlatform {

    public static void main(String[] args) {
        SpringApplication.run(DocumentCollaborationPlatform.class, args);
    }

    // Root Controller
    @GetMapping("/")
    public String home() {
        return "Welcome to the Document Collaboration Platform!";
    }

    // Exception Handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
    }

    // Additional controllers and services can be added here
}
