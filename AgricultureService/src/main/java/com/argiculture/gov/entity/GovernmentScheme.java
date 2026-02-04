package com.agriculture.gov.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "government_schemes")
public class GovernmentScheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String schemeName;

    @NotBlank
    @Column(nullable = false)
    private String schemeNameKannada;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String descriptionKannada;

    @Column(columnDefinition = "TEXT")
    private String eligibility;

    @Column(columnDefinition = "TEXT")
    private String documentsRequired;

    private String applicationLink;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SchemeStatus status;

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

    public enum SchemeStatus {
        ACTIVE, INACTIVE, EXPIRED
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSchemeName() { return schemeName; }
    public void setSchemeName(String schemeName) { this.schemeName = schemeName; }

    public String getSchemeNameKannada() { return schemeNameKannada; }
    public void setSchemeNameKannada(String schemeNameKannada) { this.schemeNameKannada = schemeNameKannada; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDescriptionKannada() { return descriptionKannada; }
    public void setDescriptionKannada(String descriptionKannada) { this.descriptionKannada = descriptionKannada; }

    public String getEligibility() { return eligibility; }
    public void setEligibility(String eligibility) { this.eligibility = eligibility; }

    public String getDocumentsRequired() { return documentsRequired; }
    public void setDocumentsRequired(String documentsRequired) { this.documentsRequired = documentsRequired; }

    public String getApplicationLink() { return applicationLink; }
    public void setApplicationLink(String applicationLink) { this.applicationLink = applicationLink; }

    public SchemeStatus getStatus() { return status; }
    public void setStatus(SchemeStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}