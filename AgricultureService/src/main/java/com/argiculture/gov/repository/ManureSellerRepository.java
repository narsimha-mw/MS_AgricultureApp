package com.agriculture.gov.repository;

import com.agriculture.gov.entity.ManureSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ManureSellerRepository extends JpaRepository<ManureSeller, Long> {
    List<ManureSeller> findByIsActiveTrueOrderByCreatedAtDesc();
    
    List<ManureSeller> findByDistrictAndIsActiveTrueOrderByCreatedAtDesc(String district);
    
    List<ManureSeller> findByManureTypeAndIsActiveTrueOrderByCreatedAtDesc(ManureSeller.ManureType manureType);
    
    @Query("SELECT ms FROM ManureSeller ms WHERE ms.isActive = true AND " +
           "(6371 * acos(cos(radians(:latitude)) * cos(radians(ms.latitude)) * " +
           "cos(radians(ms.longitude) - radians(:longitude)) + " +
           "sin(radians(:latitude)) * sin(radians(ms.latitude)))) <= :radiusKm " +
           "ORDER BY (6371 * acos(cos(radians(:latitude)) * cos(radians(ms.latitude)) * " +
           "cos(radians(ms.longitude) - radians(:longitude)) + " +
           "sin(radians(:latitude)) * sin(radians(ms.latitude))))")
    List<ManureSeller> findSellersWithinRadius(@Param("latitude") BigDecimal latitude, 
                                               @Param("longitude") BigDecimal longitude, 
                                               @Param("radiusKm") Double radiusKm);
    
    @Query("SELECT DISTINCT ms.district FROM ManureSeller ms WHERE ms.isActive = true ORDER BY ms.district")
    List<String> findAllActiveDistricts();
}