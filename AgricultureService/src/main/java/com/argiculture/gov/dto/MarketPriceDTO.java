package com.agriculture.gov.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MarketPriceDTO {
    private Long id;
    private String commodity;
    private String commodityKannada;
    private String market;
    private String district;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private BigDecimal modalPrice;
    private LocalDate priceDate;
    private String unit;

    // Constructors
    public MarketPriceDTO() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCommodity() { return commodity; }
    public void setCommodity(String commodity) { this.commodity = commodity; }

    public String getCommodityKannada() { return commodityKannada; }
    public void setCommodityKannada(String commodityKannada) { this.commodityKannada = commodityKannada; }

    public String getMarket() { return market; }
    public void setMarket(String market) { this.market = market; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public BigDecimal getMinPrice() { return minPrice; }
    public void setMinPrice(BigDecimal minPrice) { this.minPrice = minPrice; }

    public BigDecimal getMaxPrice() { return maxPrice; }
    public void setMaxPrice(BigDecimal maxPrice) { this.maxPrice = maxPrice; }

    public BigDecimal getModalPrice() { return modalPrice; }
    public void setModalPrice(BigDecimal modalPrice) { this.modalPrice = modalPrice; }

    public LocalDate getPriceDate() { return priceDate; }
    public void setPriceDate(LocalDate priceDate) { this.priceDate = priceDate; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}