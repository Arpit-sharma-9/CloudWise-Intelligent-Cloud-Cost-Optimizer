package com.cloudwise.repository;

import com.cloudwise.entity.CloudResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CloudResourceRepository interface.
 *
 * <p>Spring Data JPA automatically provides the implementation at runtime.
 * We extend JpaRepository to get basic CRUD operations for free.</p>
 *
 * <p>No custom methods are needed because the dashboard statistics are
 * calculated in the service layer using the findAll() method.</p>
 */
@Repository
public interface CloudResourceRepository extends JpaRepository<CloudResource, Long> {
}
