package com.agriculture.gov.serviceimpl;

import com.agriculture.gov.entity.ManureSeller;
import com.agriculture.gov.exception.ResourceNotFoundException;
import com.agriculture.gov.repository.ManureSellerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ManureSellerServiceimpl implements ManureSellerService {
    
    private static final Logger logger = LoggerFactory.getLogger(ManureSellerService.class);
    
    @Autowired
    private ManureSellerRepository manureSellerRepository;

    public List<ManureSeller> getAllActiveSellers() {
        logger.info("Fetching all active manure sellers");
        try {
            return manureSellerRepository.findByIsActiveTrueOrderByCreatedAtDesc();
        } catch (Exception e) {
            logger.error("Error fetching active sellers: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch manure sellers", e);
        }
    }

    public List<ManureSeller> getSellersByDistrict(String district) {
        logger.info("Fetching sellers for district: {}", district);
        try {
            return manureSellerRepository.findByDistrictAndIsActiveTrueOrderByCreatedAtDesc(district);
        } catch (Exception e) {
            logger.error("Error fetching sellers for district {}: {}", district, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch sellers for district: " + district, e);
        }
    }

    public List<ManureSeller> getSellersByManureType(ManureSeller.ManureType manureType) {
        logger.info("Fetching sellers for manure type: {}", manureType);
        try {
            return manureSellerRepository.findByManureTypeAndIsActiveTrueOrderByCreatedAtDesc(manureType);
        } catch (Exception e) {
            logger.error("Error fetching sellers for manure type {}: {}", manureType, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch sellers for manure type: " + manureType, e);
        }
    }

    public List<ManureSeller> getSellersNearby(BigDecimal latitude, BigDecimal longitude, Double radiusKm) {
        logger.info("Fetching sellers within {}km of coordinates: {}, {}", radiusKm, latitude, longitude);
        try {
            return manureSellerRepository.findSellersWithinRadius(latitude, longitude, radiusKm);
        } catch (Exception e) {
            logger.error("Error fetching nearby sellers: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch nearby sellers", e);
        }
    }

    public ManureSeller getSellerById(Long id) {
        logger.info("Fetching seller with id: {}", id);
        return manureSellerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manure seller not found with id: " + id));
    }

    public ManureSeller registerSeller(ManureSeller seller) {
        logger.info("Registering new manure seller: {}", seller.getSellerName());
        try {
            ManureSeller savedSeller = manureSellerRepository.save(seller);
            logger.info("Successfully registered seller with id: {}", savedSeller.getId());
            return savedSeller;
        } catch (Exception e) {
            logger.error("Error registering seller: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to register manure seller", e);
        }
    }

    public ManureSeller updateSeller(Long id, ManureSeller sellerDetails) {
        logger.info("Updating seller with id: {}", id);
        ManureSeller seller = manureSellerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manure seller not found with id: " + id));

        seller.setSellerName(sellerDetails.getSellerName());
        seller.setContactNumber(sellerDetails.getContactNumber());
        seller.setLatitude(sellerDetails.getLatitude());
        seller.setLongitude(sellerDetails.getLongitude());
        seller.setAddress(sellerDetails.getAddress());
        seller.setDistrict(sellerDetails.getDistrict());
        seller.setVillage(sellerDetails.getVillage());
        seller.setManureType(sellerDetails.getManureType());
        seller.setPricePerUnit(sellerDetails.getPricePerUnit());
        seller.setUnit(sellerDetails.getUnit());
        seller.setDescription(sellerDetails.getDescription());
        seller.setImageUrl(sellerDetails.getImageUrl());
        seller.setIsActive(sellerDetails.getIsActive());

        try {
            ManureSeller updatedSeller = manureSellerRepository.save(seller);
            logger.info("Successfully updated seller with id: {}", updatedSeller.getId());
            return updatedSeller;
        } catch (Exception e) {
            logger.error("Error updating seller: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to update manure seller", e);
        }
    }

    @Cacheable(value = "activeDistricts")
    public List<String> getAllActiveDistricts() {
        logger.info("Fetching all active districts");
        return manureSellerRepository.findAllActiveDistricts();
    }
}