package com.agriculture.gov.service;

import com.agriculture.dto.gov.GovernmentSchemeDTO;
import com.agriculture.entity.gov.GovernmentScheme;
import com.agriculture.exception.gov.ResourceNotFoundException;
import com.agriculture.repository.gov.GovernmentSchemeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GovernmentSchemeService {
    
    private static final Logger logger = LoggerFactory.getLogger(GovernmentSchemeService.class);
    
    @Autowired
    private GovernmentSchemeRepository schemeRepository;

    @Cacheable(value = "activeSchemes")
    public List<GovernmentSchemeDTO> getAllActiveSchemes() {
        logger.info("Fetching all active government schemes");
        try {
            List<GovernmentScheme> schemes = schemeRepository.findActiveSchemes();
            return schemes.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching active schemes: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch government schemes", e);
        }
    }

    public List<GovernmentSchemeDTO> searchSchemes(String keyword) {
        logger.info("Searching schemes with keyword: {}", keyword);
        try {
            List<GovernmentScheme> schemes = schemeRepository.searchActiveSchemes(keyword);
            return schemes.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error searching schemes: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to search government schemes", e);
        }
    }

    public GovernmentSchemeDTO getSchemeById(Long id) {
        logger.info("Fetching scheme with id: {}", id);
        GovernmentScheme scheme = schemeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Government scheme not found with id: " + id));
        return convertToDTO(scheme);
    }

    public GovernmentSchemeDTO createScheme(GovernmentScheme scheme) {
        logger.info("Creating new government scheme: {}", scheme.getSchemeName());
        try {
            GovernmentScheme savedScheme = schemeRepository.save(scheme);
            logger.info("Successfully created scheme with id: {}", savedScheme.getId());
            return convertToDTO(savedScheme);
        } catch (Exception e) {
            logger.error("Error creating scheme: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create government scheme", e);
        }
    }

    public GovernmentSchemeDTO updateScheme(Long id, GovernmentScheme schemeDetails) {
        logger.info("Updating scheme with id: {}", id);
        GovernmentScheme scheme = schemeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Government scheme not found with id: " + id));

        scheme.setSchemeName(schemeDetails.getSchemeName());
        scheme.setSchemeNameKannada(schemeDetails.getSchemeNameKannada());
        scheme.setDescription(schemeDetails.getDescription());
        scheme.setDescriptionKannada(schemeDetails.getDescriptionKannada());
        scheme.setEligibility(schemeDetails.getEligibility());
        scheme.setDocumentsRequired(schemeDetails.getDocumentsRequired());
        scheme.setApplicationLink(schemeDetails.getApplicationLink());
        scheme.setStatus(schemeDetails.getStatus());

        try {
            GovernmentScheme updatedScheme = schemeRepository.save(scheme);
            logger.info("Successfully updated scheme with id: {}", updatedScheme.getId());
            return convertToDTO(updatedScheme);
        } catch (Exception e) {
            logger.error("Error updating scheme: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to update government scheme", e);
        }
    }

    private GovernmentSchemeDTO convertToDTO(GovernmentScheme scheme) {
        GovernmentSchemeDTO dto = new GovernmentSchemeDTO();
        dto.setId(scheme.getId());
        dto.setSchemeName(scheme.getSchemeName());
        dto.setSchemeNameKannada(scheme.getSchemeNameKannada());
        dto.setDescription(scheme.getDescription());
        dto.setDescriptionKannada(scheme.getDescriptionKannada());
        dto.setEligibility(scheme.getEligibility());
        dto.setDocumentsRequired(scheme.getDocumentsRequired());
        dto.setApplicationLink(scheme.getApplicationLink());
        dto.setStatus(scheme.getStatus().toString());
        dto.setCreatedAt(scheme.getCreatedAt());
        return dto;
    }
}