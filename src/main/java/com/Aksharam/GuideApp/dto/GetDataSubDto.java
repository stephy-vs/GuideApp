package com.Aksharam.GuideApp.dto;

public class GetDataSubDto {
    private String Title;private String suId;private String description;private String imgUrl;
    private String imgName;private String audioUrl;private String audioName;private String videoUrl;
    private String videoName;private String referenceUrl;

    public GetDataSubDto(String title, String suId, String description, String imgUrl, String imgName, String audioUrl,
                         String audioName, String videoUrl, String videoName, String referenceUrl) {
        Title = title;
        this.suId = suId;
        this.description = description;
        this.imgUrl = imgUrl;
        this.imgName = imgName;
        this.audioUrl = audioUrl;
        this.audioName = audioName;
        this.videoUrl = videoUrl;
        this.videoName = videoName;
        this.referenceUrl = referenceUrl;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSuId() {
        return suId;
    }

    public void setSuId(String suId) {
        this.suId = suId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getReferenceUrl() {
        return referenceUrl;
    }

    public void setReferenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
    }
}
