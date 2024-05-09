package com.Aksharam.GuideApp.malayalam.subTittleMalayalam;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubTitleMalayalamRepo extends JpaRepository<SubTittleMalayalam,Integer> {
    Optional<SubTittleMalayalam> findBysuId(String stId);
}
