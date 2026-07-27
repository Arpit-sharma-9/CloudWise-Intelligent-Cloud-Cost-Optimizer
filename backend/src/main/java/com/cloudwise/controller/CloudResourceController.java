package com.cloudwise.controller;

import com.cloudwise.dto.CloudResourceRequest;
import com.cloudwise.dto.CloudResourceResponse;
import com.cloudwise.dto.DashboardResponse;
import com.cloudwise.service.CloudResourceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CloudResourceController exposes the REST APIs for the CloudWise application.
 *
 * <p>All endpoints are mapped under "/api". Cross-origin requests from the
 * Angular development server (http://localhost:4200) are allowed.</p>
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CloudResourceController {

    private final CloudResourceService service;

    /**
     * Constructor-based injection.
     *
     * @param service the CloudResourceService instance injected by Spring
     */
    public CloudResourceController(CloudResourceService service) {
        this.service = service;
    }

    /**
     * GET /api/resources
     * Returns all cloud resources.
     */
    @GetMapping("/resources")
    public ResponseEntity<List<CloudResourceResponse>> getAllResources() {
        List<CloudResourceResponse> resources = service.getAllResources();
        return ResponseEntity.ok(resources);
    }

    /**
     * GET /api/resources/{id}
     * Returns a single cloud resource by its id.
     */
    @GetMapping("/resources/{id}")
    public ResponseEntity<CloudResourceResponse> getResourceById(@PathVariable Long id) {
        CloudResourceResponse resource = service.getResourceById(id);
        return ResponseEntity.ok(resource);
    }

    /**
     * POST /api/resources
     * Creates a new cloud resource.
     */
    @PostMapping("/resources")
    public ResponseEntity<CloudResourceResponse> createResource(
            @Valid @RequestBody CloudResourceRequest request) {
        CloudResourceResponse created = service.createResource(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * PUT /api/resources/{id}
     * Updates an existing cloud resource.
     */
    @PutMapping("/resources/{id}")
    public ResponseEntity<CloudResourceResponse> updateResource(
            @PathVariable Long id,
            @Valid @RequestBody CloudResourceRequest request) {
        CloudResourceResponse updated = service.updateResource(id, request);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/resources/{id}
     * Deletes a cloud resource.
     */
    @DeleteMapping("/resources/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        service.deleteResource(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/dashboard
     * Returns aggregated dashboard statistics.
     */
    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponse> getDashboard() {
        DashboardResponse dashboard = service.getDashboardStatistics();
        return ResponseEntity.ok(dashboard);
    }
}
