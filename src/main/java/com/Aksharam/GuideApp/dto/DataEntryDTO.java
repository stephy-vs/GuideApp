package com.Aksharam.GuideApp.dto;

public class DataEntryDTO {
    private String title;

    private String description;


    private String referenceUrl;

    public DataEntryDTO(String title, String description, String referenceUrl) {
        this.title = title;
        this.description = description;
        this.referenceUrl = referenceUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferenceUrl() {
        return referenceUrl;
    }

    public void setReferenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
    }
}
