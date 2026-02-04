package com.agriculture.gov.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "market_prices")
public class MarketPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String commodity;

    @NotBlank
    @Column(nullable = false)
    private String commodityKannada;

    @NotBlank
    @Column(nullable = false)
    private String market;

    @NotBlank
    @Column(nullable = false)
    private String district;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal minPrice;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal maxPrice;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal modalPrice;

    @NotNull
    @Column(nullable = false)
    private LocalDate priceDate;

    private String unit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

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

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}