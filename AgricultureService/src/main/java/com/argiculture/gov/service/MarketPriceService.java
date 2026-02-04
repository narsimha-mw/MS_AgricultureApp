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

@Service
@Transactional
public class MarketPriceService {
    
    private static final Logger logger = LoggerFactory.getLogger(MarketPriceService.class);
    
    @Autowired
    private MarketPriceRepository marketPriceRepository;
    
    @Autowired
    private WebClient.Builder webClientBuilder;
    
    @Value("${external.apis.agmarknet.url}")
    private String agmarknetUrl;
    
    @Value("${external.apis.agmarknet.key}")
    private String agmarknetKey;

    @Cacheable(value = "latestPrices")
    public List<MarketPriceDTO> getLatestPrices() {
        logger.info("Fetching latest market prices");
        try {
            List<MarketPrice> prices = marketPriceRepository.findLatestPrices();
            return prices.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching latest prices: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch market prices", e);
        }
    }

    public List<MarketPriceDTO> getPricesByDistrict(String district) {
        logger.info("Fetching prices for district: {}", district);
        try {
            LocalDate today = LocalDate.now();
            List<MarketPrice> prices = marketPriceRepository.findByDistrictAndPriceDateOrderByCreatedAtDesc(district, today);
            return prices.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching prices for district {}: {}", district, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch prices for district: " + district, e);
        }
    }

    public List<MarketPriceDTO> getPricesByCommodity(String commodity) {
        logger.info("Fetching prices for commodity: {}", commodity);
        try {
            LocalDate today = LocalDate.now();
            List<MarketPrice> prices = marketPriceRepository.findByCommodityAndPriceDateOrderByCreatedAtDesc(commodity, today);
            return prices.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching prices for commodity {}: {}", commodity, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch prices for commodity: " + commodity, e);
        }
    }

    @Cacheable(value = "districts")
    public List<String> getAllDistricts() {
        logger.info("Fetching all districts");
        return marketPriceRepository.findAllDistricts();
    }

    @Cacheable(value = "commodities")
    public List<String> getAllCommodities() {
        logger.info("Fetching all commodities");
        return marketPriceRepository.findAllCommodities();
    }

    @Scheduled(cron = "0 0 6 * * ?") // Run daily at 6 AM
    public void fetchAndUpdatePrices() {
        logger.info("Starting scheduled price update from Agmarknet API");
        try {
            // This would integrate with actual Agmarknet API
            // For now, we'll log the scheduled execution
            logger.info("Price update scheduled task executed successfully");
        } catch (Exception e) {
            logger.error("Error in scheduled price update: {}", e.getMessage(), e);
        }
    }

    private MarketPriceDTO convertToDTO(MarketPrice price) {
        MarketPriceDTO dto = new MarketPriceDTO();
        dto.setId(price.getId());
        dto.setCommodity(price.getCommodity());
        dto.setCommodityKannada(price.getCommodityKannada());
        dto.setMarket(price.getMarket());
        dto.setDistrict(price.getDistrict());
        dto.setMinPrice(price.getMinPrice());
        dto.setMaxPrice(price.getMaxPrice());
        dto.setModalPrice(price.getModalPrice());
        dto.setPriceDate(price.getPriceDate());
        dto.setUnit(price.getUnit());
        return dto;
    }
}