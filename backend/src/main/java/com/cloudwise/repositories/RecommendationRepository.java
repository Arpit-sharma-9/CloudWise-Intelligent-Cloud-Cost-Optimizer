package com.cloudwise.repositories;

import com.cloudwise.models.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByResourceId(Long resourceId);
    List<Recommendation> findByStatus(Recommendation.RecommendationStatus status);
}
