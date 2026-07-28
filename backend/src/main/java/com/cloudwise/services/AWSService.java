package com.cloudwise.services;

import com.cloudwise.models.AWSResource;
import com.cloudwise.repositories.AWSResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AWSService {

    @Autowired
    private AWSResourceRepository awsResourceRepository;

    public boolean connectAWS(String roleArn) {
        // Logic to connect to AWS using IAM role
        // For now, return true as a placeholder
        return true;
    }

    public List<AWSResource> fetchAWSResources(Long userId) {
        // Placeholder: Fetch AWS resources from AWS SDK
        List<AWSResource> resources = new ArrayList<>();
        
        // Example: Add a dummy EC2 instance
        AWSResource ec2Instance = new AWSResource();
        ec2Instance.setUserId(userId);
        ec2Instance.setResourceType(AWSResource.ResourceType.EC2);
        ec2Instance.setResourceId("i-1234567890");
        ec2Instance.setResourceName("Web Server");
        ec2Instance.setRegion("us-east-1");
        ec2Instance.setCost(new BigDecimal("72.00"));
        ec2Instance.setCpuUtilization(new BigDecimal("3.00"));
        ec2Instance.setIsActive(true);
        ec2Instance.setCreatedAt(LocalDateTime.now());
        resources.add(ec2Instance);

        // Save to database
        awsResourceRepository.saveAll(resources);
        
        return resources;
    }

    public String calculateCurrentCosts(Long userId) {
        List<AWSResource> resources = awsResourceRepository.findByUserId(userId);
        BigDecimal totalCost = resources.stream()
                .map(AWSResource::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return "Total Cost: $" + totalCost.toString();
    }
}
