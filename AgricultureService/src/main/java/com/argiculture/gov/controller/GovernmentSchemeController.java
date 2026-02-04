package com.agriculture.gov.controller;

import com.agriculture.gov.dto.ApiResponse;
import com.agriculture.gov.dto.GovernmentSchemeDTO;
import com.agriculture.gov.entity.GovernmentScheme;
import com.agriculture.gov.service.GovernmentSchemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schemes")
@CrossOrigin(origins = "*")
public class GovernmentSchemeController {
    
    private static final Logger logger = LoggerFactory.getLogger(GovernmentSchemeController.class);
    
    @Autowired
    private GovernmentSchemeService schemeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<GovernmentSchemeDTO>>> getAllActiveSchemes() {
        logger.info("GET /api/v1/schemes - Fetching all active schemes");
        try {
            List<GovernmentSchemeDTO> schemes = schemeService.getAllActiveSchemes();
            return ResponseEntity.ok(ApiResponse.success("Active schemes retrieved successfully", schemes));
        } catch (Exception e) {
            logger.error("Error fetching schemes: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch government schemes"));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<GovernmentSchemeDTO>>> searchSchemes(@RequestParam String keyword) {
        logger.info("GET /api/v1/schemes/search - Searching schemes with keyword: {}", keyword);
        try {
            List<GovernmentSchemeDTO> schemes = schemeService.searchSchemes(keyword);
            return ResponseEntity.ok(ApiResponse.success("Search results retrieved successfully", schemes));
        } catch (Exception e) {
            logger.error("Error searching schemes: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to search government schemes"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GovernmentSchemeDTO>> getSchemeById(@PathVariable Long id) {
        logger.info("GET /api/v1/schemes/{} - Fetching scheme by id", id);
        try {
            GovernmentSchemeDTO scheme = schemeService.getSchemeById(id);
            return ResponseEntity.ok(ApiResponse.success("Scheme retrieved successfully", scheme));
        } catch (Exception e) {
            logger.error("Error fetching scheme by id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Government scheme not found"));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GovernmentSchemeDTO>> createScheme(@Valid @RequestBody GovernmentScheme scheme) {
        logger.info("POST /api/v1/schemes - Creating new scheme: {}", scheme.getSchemeName());
        try {
            GovernmentSchemeDTO createdScheme = schemeService.createScheme(scheme);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Scheme created successfully", createdScheme));
        } catch (Exception e) {
            logger.error("Error creating scheme: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to create government scheme"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GovernmentSchemeDTO>> updateScheme(@PathVariable Long id, 
                                                                        @Valid @RequestBody GovernmentScheme schemeDetails) {
        logger.info("PUT /api/v1/schemes/{} - Updating scheme", id);
        try {
            GovernmentSchemeDTO updatedScheme = schemeService.updateScheme(id, schemeDetails);
            return ResponseEntity.ok(ApiResponse.success("Scheme updated successfully", updatedScheme));
        } catch (Exception e) {
            logger.error("Error updating scheme {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to update government scheme"));
        }
    }
}