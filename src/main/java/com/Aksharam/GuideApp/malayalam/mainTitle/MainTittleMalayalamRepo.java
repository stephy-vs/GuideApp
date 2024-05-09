package com.Aksharam.GuideApp.malayalam.mainTitle;

import com.Aksharam.GuideApp.malayalam.mainTitle.MainTittleMalayalam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainTittleMalayalamRepo extends JpaRepository<MainTittleMalayalam,Integer> {
    Optional<MainTittleMalayalam> findByuId(String mId);
}
