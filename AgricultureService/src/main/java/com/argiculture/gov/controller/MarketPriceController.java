package com.agriculture.gov.controller;

import com.agriculture.gov.dto.ApiResponse;
import com.agriculture.gov.dto.MarketPriceDTO;
import com.agriculture.gov.service.MarketPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/market-prices")
@CrossOrigin(origins = "*")
public class MarketPriceController {
    
    private static final Logger logger = LoggerFactory.getLogger(MarketPriceController.class);
    
    @Autowired
    private MarketPriceService marketPriceService;

    @GetMapping("/latest")
    public ResponseEntity<ApiResponse<List<MarketPriceDTO>>> getLatestPrices() {
        logger.info("GET /api/v1/market-prices/latest - Fetching latest market prices");
        try {
            List<MarketPriceDTO> prices = marketPriceService.getLatestPrices();
            return ResponseEntity.ok(ApiResponse.success("Latest prices retrieved successfully", prices));
        } catch (Exception e) {
            logger.error("Error fetching latest prices: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch market prices"));
        }
    }

    @GetMapping("/district/{district}")
    public ResponseEntity<ApiResponse<List<MarketPriceDTO>>> getPricesByDistrict(@PathVariable String district) {
        logger.info("GET /api/v1/market-prices/district/{} - Fetching prices by district", district);
        try {
            List<MarketPriceDTO> prices = marketPriceService.getPricesByDistrict(district);
            return ResponseEntity.ok(ApiResponse.success("District prices retrieved successfully", prices));
        } catch (Exception e) {
            logger.error("Error fetching prices for district {}: {}", district, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch prices for district"));
        }
    }

    @GetMapping("/commodity/{commodity}")
    public ResponseEntity<ApiResponse<List<MarketPriceDTO>>> getPricesByCommodity(@PathVariable String commodity) {
        logger.info("GET /api/v1/market-prices/commodity/{} - Fetching prices by commodity", commodity);
        try {
            List<MarketPriceDTO> prices = marketPriceService.getPricesByCommodity(commodity);
            return ResponseEntity.ok(ApiResponse.success("Commodity prices retrieved successfully", prices));
        } catch (Exception e) {
            logger.error("Error fetching prices for commodity {}: {}", commodity, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch prices for commodity"));
        }
    }

    @GetMapping("/districts")
    public ResponseEntity<ApiResponse<List<String>>> getAllDistricts() {
        logger.info("GET /api/v1/market-prices/districts - Fetching all districts");
        try {
            List<String> districts = marketPriceService.getAllDistricts();
            return ResponseEntity.ok(ApiResponse.success("Districts retrieved successfully", districts));
        } catch (Exception e) {
            logger.error("Error fetching districts: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch districts"));
        }
    }

    @GetMapping("/commodities")
    public ResponseEntity<ApiResponse<List<String>>> getAllCommodities() {
        logger.info("GET /api/v1/market-prices/commodities - Fetching all commodities");
        try {
            List<String> commodities = marketPriceService.getAllCommodities();
            return ResponseEntity.ok(ApiResponse.success("Commodities retrieved successfully", commodities));
        } catch (Exception e) {
            logger.error("Error fetching commodities: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to fetch commodities"));
        }
    }
}