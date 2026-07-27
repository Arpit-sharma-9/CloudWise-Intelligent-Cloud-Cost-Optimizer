package com.cloudwise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * CloudResourceRequest DTO (Data Transfer Object).
 *
 * <p>This object represents the JSON payload sent by the frontend when
 * creating or updating a cloud resource. It deliberately does NOT include
 * the "recommendation" field because the backend generates that
 * automatically based on the monthly cost.</p>
 *
 * <p>Bean Validation annotations ensure the input is valid before it
 * reaches the service layer.</p>
 */
public class CloudResourceRequest {

    @NotBlank(message = "Resource name is required")
    private String resourceName;

    @NotBlank(message = "Resource type is required")
    private String resourceType;

    @NotBlank(message = "Region is required")
    private String region;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Monthly cost is required")
    @PositiveOrZero(message = "Monthly cost must be zero or positive")
    private Double monthlyCost;

    public CloudResourceRequest() {
    }

    // -----------------------------------------------------------------
    // Getters and Setters
    // -----------------------------------------------------------------

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
}
