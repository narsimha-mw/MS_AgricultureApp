package com.agriculture.gov.repository;

import com.agriculture.gov.entity.GovernmentScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GovernmentSchemeRepository extends JpaRepository<GovernmentScheme, Long> {
    List<GovernmentScheme> findByStatusOrderByCreatedAtDesc(GovernmentScheme.SchemeStatus status);
    
    @Query("SELECT gs FROM GovernmentScheme gs WHERE gs.status = 'ACTIVE' ORDER BY gs.createdAt DESC")
    List<GovernmentScheme> findActiveSchemes();
    
    @Query("SELECT gs FROM GovernmentScheme gs WHERE " +
           "(LOWER(gs.schemeName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(gs.schemeNameKannada) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "AND gs.status = 'ACTIVE'")
    List<GovernmentScheme> searchActiveSchemes(String keyword);
}