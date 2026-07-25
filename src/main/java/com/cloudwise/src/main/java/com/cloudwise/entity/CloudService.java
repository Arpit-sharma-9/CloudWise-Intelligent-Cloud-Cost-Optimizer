package com.cloudwise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cloud_services")
public class CloudService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider;

    private String serviceName;

    private Double monthlyCost;

    private Double cpuUsage;

    private Double memoryUsage;

    private String region;

    public CloudService() {
    }

    public CloudService(String provider,
                        String serviceName,
                        Double monthlyCost,
                        Double cpuUsage,
                        Double memoryUsage,
                        String region) {
        this.provider = provider;
        this.serviceName = serviceName;
        this.monthlyCost = monthlyCost;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Double getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(Double monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    public Double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(Double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Double getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(Double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
