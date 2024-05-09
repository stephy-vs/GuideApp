package com.Aksharam.GuideApp.english.mainTitle;

import com.Aksharam.GuideApp.english.mainTitle.MainTitleData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainTitleDataRepo extends JpaRepository<MainTitleData,Integer> {
    Optional<MainTitleData> findByuId(String mId);
}
