package com.agriculture.gov.dto;

import java.time.LocalDateTime;

public class GovernmentSchemeDTO {
    private Long id;
    private String schemeName;
    private String schemeNameKannada;
    private String description;
    private String descriptionKannada;
    private String eligibility;
    private String documentsRequired;
    private String applicationLink;
    private String status;
    private LocalDateTime createdAt;

    // Constructors
    public GovernmentSchemeDTO() {}

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

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}