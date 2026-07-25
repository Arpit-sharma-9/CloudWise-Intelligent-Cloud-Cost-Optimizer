package com.cloudwise.repository;

import com.cloudwise.entity.CloudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudServiceRepository extends JpaRepository<CloudService, Long> {

}
