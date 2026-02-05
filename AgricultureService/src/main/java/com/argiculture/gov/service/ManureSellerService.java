package com.agriculture.gov.service;

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


public interface ManureSellerService {

    public List<ManureSeller> getAllActiveSellers();

    public List<ManureSeller> getSellersByDistrict(String district);

    public List<ManureSeller> getSellersByManureType(ManureSeller.ManureType manureType);

    public List<ManureSeller> getSellersNearby(BigDecimal latitude, BigDecimal longitude, Double radiusKm);

    public ManureSeller getSellerById(Long id);

    public ManureSeller registerSeller(ManureSeller seller);

    public ManureSeller updateSeller(Long id, ManureSeller sellerDetails) ;

    public List<String> getAllActiveDistricts();
}