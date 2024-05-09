package com.Aksharam.GuideApp.file;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "storageUrl")
public class StorageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "urlId")
    private Integer urlId;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;

    @Column(name = "qrId")
    private Integer qrId;
}
