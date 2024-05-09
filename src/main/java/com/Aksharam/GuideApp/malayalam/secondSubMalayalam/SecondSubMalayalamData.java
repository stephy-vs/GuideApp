package com.Aksharam.GuideApp.malayalam.secondSubMalayalam;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@Entity
@Table(name = "SecondSubMalayalam")
@CrossOrigin
public class SecondSubMalayalamData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "sSubId")
    private String sSubId;

    @Column(name = "fSubId")
    private String fSubId;

    @Column(name = "description")
    private String description;

    @Column(name = "imgUrl")
    private String imgUrl;

    @Column(name = "imgName")
    private String imgName;

    @Column(name = "audioUrl")
    private String audioUrl;

    @Column(name = "audioName")
    private String audioName;

    @Column(name = "videoUrl")
    private String videoUrl;

    @Column(name = "videoName")
    private String videoName;

    @Column(name = "referenceUrl")
    private String referenceUrl;

    @PrePersist
    @PreUpdate
    public void setDefault(){
        if (description == null){
            description="No Data";
        }
        if (imgUrl==null){
            imgUrl="No Data";
        }
        if (imgName==null){
            imgName="No Data";
        }
        if (audioUrl==null){
            audioUrl = "No Data";
        }
        if (audioName==null){
            audioName="No Data";
        }if (videoUrl==null){
            videoUrl="No Data";
        }if (videoName==null){
            videoName="No Data";
        }if (referenceUrl==null){
            referenceUrl="No Data";
        }
    }
}
