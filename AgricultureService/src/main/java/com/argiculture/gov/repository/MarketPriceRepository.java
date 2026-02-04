package com.agriculture.gov.repository;

import com.agriculture.gov.entity.MarketPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MarketPriceRepository extends JpaRepository<MarketPrice, Long> {
    List<MarketPrice> findByPriceDateOrderByCreatedAtDesc(LocalDate priceDate);
    
    List<MarketPrice> findByDistrictAndPriceDateOrderByCreatedAtDesc(String district, LocalDate priceDate);
    
    List<MarketPrice> findByCommodityAndPriceDateOrderByCreatedAtDesc(String commodity, LocalDate priceDate);
    
    @Query("SELECT mp FROM MarketPrice mp WHERE mp.priceDate = " +
           "(SELECT MAX(mp2.priceDate) FROM MarketPrice mp2) ORDER BY mp.commodity")
    List<MarketPrice> findLatestPrices();
    
    @Query("SELECT DISTINCT mp.district FROM MarketPrice mp ORDER BY mp.district")
    List<String> findAllDistricts();
    
    @Query("SELECT DISTINCT mp.commodity FROM MarketPrice mp ORDER BY mp.commodity")
    List<String> findAllCommodities();
}