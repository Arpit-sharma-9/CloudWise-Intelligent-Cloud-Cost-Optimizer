package com.cloudwise.controllers;

import com.cloudwise.models.AWSResource;
import com.cloudwise.services.AWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aws")
public class AWSController {

    @Autowired
    private AWSService awsService;

    @PostMapping("/connect")
    public ResponseEntity<String> connectAWS(@RequestParam String roleArn) {
        boolean isConnected = awsService.connectAWS(roleArn);
        if (isConnected) {
            return ResponseEntity.ok("AWS connected successfully!");
        } else {
            return ResponseEntity.badRequest().body("Failed to connect to AWS.");
        }
    }

    @GetMapping("/resources")
    public ResponseEntity<List<AWSResource>> getAWSResources(@RequestParam Long userId) {
        List<AWSResource> resources = awsService.fetchAWSResources(userId);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/costs")
    public ResponseEntity<String> getCurrentCosts(@RequestParam Long userId) {
        String costReport = awsService.calculateCurrentCosts(userId);
        return ResponseEntity.ok(costReport);
    }
}
