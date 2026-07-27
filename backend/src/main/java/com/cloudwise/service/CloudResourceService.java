package com.cloudwise.service;

import com.cloudwise.dto.CloudResourceRequest;
import com.cloudwise.dto.CloudResourceResponse;
import com.cloudwise.dto.DashboardResponse;
import com.cloudwise.entity.CloudResource;
import com.cloudwise.exception.ResourceNotFoundException;
import com.cloudwise.repository.CloudResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CloudResourceService contains all the business logic for managing
 * cloud resources.
 *
 * <p>The service layer sits between the controller and the repository.
 * It is responsible for:</p>
 * <ul>
 *     <li>Converting DTOs to entities and back</li>
 *     <li>Generating the recommendation automatically</li>
 *     <li>Calculating the dashboard statistics</li>
 *     <li>Throwing meaningful exceptions when a resource is not found</li>
 * </ul>
 */
@Service
public class CloudResourceService {

    private final CloudResourceRepository repository;

    /**
     * Constructor-based injection (recommended by Spring).
     *
     * @param repository the CloudResourceRepository instance injected by Spring
     */
    public CloudResourceService(CloudResourceRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns all cloud resources.
     *
     * @return list of all resources as response DTOs
     */
    public List<CloudResourceResponse> getAllResources() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Returns a single cloud resource by its id.
     *
     * @param id the id of the resource
     * @return the resource as a response DTO
     * @throws ResourceNotFoundException if no resource exists with the given id
     */
    public CloudResourceResponse getResourceById(Long id) {
        CloudResource resource = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Resource not found with id: " + id));
        return toResponse(resource);
    }

    /**
     * Creates a new cloud resource.
     * The recommendation is generated automatically based on the monthly cost.
     *
     * @param request the data sent by the user
     * @return the saved resource as a response DTO
     */
    public CloudResourceResponse createResource(CloudResourceRequest request) {
        CloudResource resource = toEntity(request);
        resource.generateRecommendation();
        CloudResource saved = repository.save(resource);
        return toResponse(saved);
    }

    /**
     * Updates an existing cloud resource.
     * The recommendation is re-generated based on the new monthly cost.
     *
     * @param id      the id of the resource to update
     * @param request the new data sent by the user
     * @return the updated resource as a response DTO
     * @throws ResourceNotFoundException if no resource exists with the given id
     */
    public CloudResourceResponse updateResource(Long id, CloudResourceRequest request) {
        CloudResource existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Resource not found with id: " + id));

        existing.setResourceName(request.getResourceName());
        existing.setResourceType(request.getResourceType());
        existing.setRegion(request.getRegion());
        existing.setStatus(request.getStatus());
        existing.setMonthlyCost(request.getMonthlyCost());
        existing.generateRecommendation();

        CloudResource updated = repository.save(existing);
        return toResponse(updated);
    }

    /**
     * Deletes a cloud resource by its id.
     *
     * @param id the id of the resource to delete
     * @throws ResourceNotFoundException if no resource exists with the given id
     */
    public void deleteResource(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Resource not found with id: " + id);
        }
        repository.deleteById(id);
    }

    /**
     * Calculates the dashboard statistics by scanning all resources.
     *
     * @return a DashboardResponse containing total, running, stopped counts
     *         and the sum of monthly costs
     */
    public DashboardResponse getDashboardStatistics() {
        List<CloudResource> all = repository.findAll();

        long total = all.size();
        long running = all.stream()
                .filter(r -> "Running".equalsIgnoreCase(r.getStatus()))
                .count();
        long stopped = all.stream()
                .filter(r -> "Stopped".equalsIgnoreCase(r.getStatus()))
                .count();
        double totalCost = all.stream()
                .mapToDouble(CloudResource::getMonthlyCost)
                .sum();

        DashboardResponse response = new DashboardResponse();
        response.setTotalResources(total);
        response.setRunningResources(running);
        response.setStoppedResources(stopped);
        response.setTotalMonthlyCost(totalCost);
        return response;
    }

    // -----------------------------------------------------------------
    // Private helper methods for converting between entity and DTO
    // -----------------------------------------------------------------

    /**
     * Converts a request DTO into a new entity.
     */
    private CloudResource toEntity(CloudResourceRequest request) {
        CloudResource resource = new CloudResource();
        resource.setResourceName(request.getResourceName());
        resource.setResourceType(request.getResourceType());
        resource.setRegion(request.getRegion());
        resource.setStatus(request.getStatus());
        resource.setMonthlyCost(request.getMonthlyCost());
        return resource;
    }

    /**
     * Converts an entity into a response DTO.
     */
    private CloudResourceResponse toResponse(CloudResource resource) {
        CloudResourceResponse response = new CloudResourceResponse();
        response.setId(resource.getId());
        response.setResourceName(resource.getResourceName());
        response.setResourceType(resource.getResourceType());
        response.setRegion(resource.getRegion());
        response.setStatus(resource.getStatus());
        response.setMonthlyCost(resource.getMonthlyCost());
        response.setRecommendation(resource.getRecommendation());
        response.setCreatedDate(resource.getCreatedDate());
        return response;
    }
}
