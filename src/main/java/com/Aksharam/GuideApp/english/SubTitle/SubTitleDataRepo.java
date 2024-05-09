package com.Aksharam.GuideApp.english.SubTitle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubTitleDataRepo extends JpaRepository<SubTitleData,Integer> {
    Optional<SubTitleData> findBysuId(String stId);
}
