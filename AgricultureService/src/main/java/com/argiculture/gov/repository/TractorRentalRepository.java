package com.agriculture.gov.repository;

import com.agriculture.gov.entity.TractorRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TractorRentalRepository extends JpaRepository<TractorRental, Long> {
    List<TractorRental> findByIsAvailableTrueOrderByCreatedAtDesc();
    
    List<TractorRental> findByDistrictAndIsAvailableTrueOrderByCreatedAtDesc(String district);
    
    @Query("SELECT tr FROM TractorRental tr WHERE tr.isAvailable = true AND " +
           "(6371 * acos(cos(radians(:latitude)) * cos(radians(tr.latitude)) * " +
           "cos(radians(tr.longitude) - radians(:longitude)) + " +
           "sin(radians(:latitude)) * sin(radians(tr.latitude)))) <= :radiusKm " +
           "ORDER BY (6371 * acos(cos(radians(:latitude)) * cos(radians(tr.latitude)) * " +
           "cos(radians(tr.longitude) - radians(:longitude)) + " +
           "sin(radians(:latitude)) * sin(radians(tr.latitude))))")
    List<TractorRental> findTractorsWithinRadius(@Param("latitude") BigDecimal latitude, 
                                                 @Param("longitude") BigDecimal longitude, 
                                                 @Param("radiusKm") Double radiusKm);
    
    List<TractorRental> findByHorsePowerBetweenAndIsAvailableTrueOrderByPricePerHour(Integer minHp, Integer maxHp);
}