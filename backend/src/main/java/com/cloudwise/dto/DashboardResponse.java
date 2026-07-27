package com.cloudwise.dto;

/**
 * DashboardResponse DTO.
 *
 * <p>This object holds the aggregated statistics shown on the dashboard
 * page: total number of resources, how many are running, how many are
 * stopped, and the sum of all monthly costs.</p>
 */
public class DashboardResponse {

    private long totalResources;
    private long runningResources;
    private long stoppedResources;
    private double totalMonthlyCost;

    public DashboardResponse() {
    }

    // -----------------------------------------------------------------
    // Getters and Setters
    // -----------------------------------------------------------------

    public long getTotalResources() {
        return totalResources;
    }

    public void setTotalResources(long totalResources) {
        this.totalResources = totalResources;
    }

    public long getRunningResources() {
        return runningResources;
    }

    public void setRunningResources(long runningResources) {
        this.runningResources = runningResources;
    }

    public long getStoppedResources() {
        return stoppedResources;
    }

    public void setStoppedResources(long stoppedResources) {
        this.stoppedResources = stoppedResources;
    }

    public double getTotalMonthlyCost() {
        return totalMonthlyCost;
    }

    public void setTotalMonthlyCost(double totalMonthlyCost) {
        this.totalMonthlyCost = totalMonthlyCost;
    }
}
