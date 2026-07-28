package com.cloudwise.repositories;

import com.cloudwise.models.AWSResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AWSResourceRepository extends JpaRepository<AWSResource, Long> {
    List<AWSResource> findByUserId(Long userId);
    List<AWSResource> findByResourceType(AWSResource.ResourceType resourceType);
    List<AWSResource> findByIsActive(Boolean isActive);
}
