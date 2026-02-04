package com.agriculture.gov.controller;

import com.agriculture.gov.dto.ApiResponse;
import com.agriculture.gov.entity.ManureSeller;
import com.agriculture.gov.service.ManureSellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/manure-sellers")
@CrossOrigin(origins = "*")
public class ManureSellerController {
    
    private static final Logger logger = LoggerFactory.getLogger(ManureSellerController.class);
    
    @Autowired
    private ManureSellerService manureSellerService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ManureSeller>>> getAllActiveSellers() {
        logger.info("GET /api/v1/manure-sellers - Fetching all active sellers");
        try {
            List<ManureSeller> sellers = manureSellerService.getAllActiveSellers();
            return ResponseEntity.ok(ApiResponse.success("Active sellers retrieved successfully", sellers));
        } catch (Exception e) {
            logger.error("Error fetching active sellers: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch manure sellers"));
        }
    }

    @GetMapping("/district/{district}")
    public ResponseEntity<ApiResponse<List<ManureSeller>>> getSellersByDistrict(@PathVariable String district) {
        logger.info("GET /api/v1/manure-sellers/district/{} - Fetching sellers by district", district);
        try {
            List<ManureSeller> sellers = manureSellerService.getSellersByDistrict(district);
            return ResponseEntity.ok(ApiResponse.success("District sellers retrieved successfully", sellers));
        } catch (Exception e) {
            logger.error("Error fetching sellers for district {}: {}", district, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch sellers for district"));
        }
    }

    @GetMapping("/type/{manureType}")
    public ResponseEntity<ApiResponse<List<ManureSeller>>> getSellersByManureType(@PathVariable ManureSeller.ManureType manureType) {
        logger.info("GET /api/v1/manure-sellers/type/{} - Fetching sellers by manure type", manureType);
        try {
            List<ManureSeller> sellers = manureSellerService.getSellersByManureType(manureType);
            return ResponseEntity.ok(ApiResponse.success("Manure type sellers retrieved successfully", sellers));
        } catch (Exception e) {
            logger.error("Error fetching sellers for manure type {}: {}", manureType, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch sellers for manure type"));
        }
    }

    @GetMapping("/nearby")
    public ResponseEntity<ApiResponse<List<ManureSeller>>> getSellersNearby(
            @RequestParam BigDecimal latitude,
            @RequestParam BigDecimal longitude,
            @RequestParam(defaultValue = "10.0") Double radiusKm) {
        logger.info("GET /api/v1/manure-sellers/nearby - Fetching sellers within {}km of {}, {}", radiusKm, latitude, longitude);
        try {
            List<ManureSeller> sellers = manureSellerService.getSellersNearby(latitude, longitude, radiusKm);
            return ResponseEntity.ok(ApiResponse.success("Nearby sellers retrieved successfully", sellers));
        } catch (Exception e) {
            logger.error("Error fetching nearby sellers: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch nearby sellers"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ManureSeller>> getSellerById(@PathVariable Long id) {
        logger.info("GET /api/v1/manure-sellers/{} - Fetching seller by id", id);
        try {
            ManureSeller seller = manureSellerService.getSellerById(id);
            return ResponseEntity.ok(ApiResponse.success("Seller retrieved successfully", seller));
        } catch (Exception e) {
            logger.error("Error fetching seller by id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Manure seller not found"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<ManureSeller>> registerSeller(@Valid @RequestBody ManureSeller seller) {
        logger.info("POST /api/v1/manure-sellers/register - Registering new seller: {}", seller.getSellerName());
        try {
            ManureSeller registeredSeller = manureSellerService.registerSeller(seller);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Seller registered successfully", registeredSeller));
        } catch (Exception e) {
            logger.error("Error registering seller: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to register manure seller"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ManureSeller>> updateSeller(@PathVariable Long id, 
                                                                 @Valid @RequestBody ManureSeller sellerDetails) {
        logger.info("PUT /api/v1/manure-sellers/{} - Updating seller", id);
        try {
            ManureSeller updatedSeller = manureSellerService.updateSeller(id, sellerDetails);
            return ResponseEntity.ok(ApiResponse.success("Seller updated successfully", updatedSeller));
        } catch (Exception e) {
            logger.error("Error updating seller {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to update manure seller"));
        }
    }

    @GetMapping("/districts")
    public ResponseEntity<ApiResponse<List<String>>> getAllActiveDistricts() {
        logger.info("GET /api/v1/manure-sellers/districts - Fetching all active districts");
        try {
            List<String> districts = manureSellerService.getAllActiveDistricts();
            return ResponseEntity.ok(ApiResponse.success("Active districts retrieved successfully", districts));
        } catch (Exception e) {
            logger.error("Error fetching active districts: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch active districts"));
        }
    }
}