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

public interface GovernmentSchemeService {
    
   
    public List<GovernmentSchemeDTO> getAllActiveSchemes();

    public List<GovernmentSchemeDTO> searchSchemes(String keyword);

    public GovernmentSchemeDTO getSchemeById(Long id);

    public GovernmentSchemeDTO createScheme(GovernmentScheme scheme);

    public GovernmentSchemeDTO updateScheme(Long id, GovernmentScheme schemeDetails) ;

}