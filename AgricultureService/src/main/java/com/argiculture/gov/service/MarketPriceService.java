package com.agriculture.gov.service;

import com.agriculture.gov.dto.MarketPriceDTO;
import com.agriculture.gov.entity.MarketPrice;
import com.agriculture.gov.repository.MarketPriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public interface MarketPriceService {

    public List<MarketPriceDTO> getLatestPrices() ;

    public List<MarketPriceDTO> getPricesByDistrict(String district) ;

    public List<MarketPriceDTO> getPricesByCommodity(String commodity);

    public List<String> getAllDistricts() ;

    public List<String> getAllCommodities();

    public void fetchAndUpdatePrices() ;
