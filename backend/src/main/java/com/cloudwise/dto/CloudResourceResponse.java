package com.cloudwise.dto;

import java.time.LocalDateTime;

/**
 * CloudResourceResponse DTO.
 *
 * <p>This object represents the JSON payload sent back to the frontend
 * when returning a single cloud resource. It includes the id, the
 * recommendation, and the createdDate which are not present in the
 * request DTO.</p>
 */
public class CloudResourceResponse {

    private Long id;
    private String resourceName;
    private String resourceType;
    private String region;
    private String status;
    private Double monthlyCost;
    private String recommendation;
    private LocalDateTime createdDate;

    public CloudResourceResponse() {
    }

    // -----------------------------------------------------------------
    // Getters and Setters
    // -----------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(Double monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
