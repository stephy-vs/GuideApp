package com.Aksharam.GuideApp.guide;

import com.Aksharam.GuideApp.dto.DataEntryDTO;
import com.Aksharam.GuideApp.dto.GetDataDTO;
import com.Aksharam.GuideApp.dto.GetDataSDTO;
import com.Aksharam.GuideApp.dto.GetDataSubDto;
import com.Aksharam.GuideApp.english.SubTitle.SubTitleData;
import com.Aksharam.GuideApp.english.SubTitle.SubTitleDataRepo;
import com.Aksharam.GuideApp.english.mainTitle.MainTitleData;
import com.Aksharam.GuideApp.english.mainTitle.MainTitleDataRepo;
import com.Aksharam.GuideApp.language.DataType;
import com.Aksharam.GuideApp.language.DataTypeRepo;
import com.Aksharam.GuideApp.malayalam.mainTitle.MainTittleMalayalam;
import com.Aksharam.GuideApp.malayalam.mainTitle.MainTittleMalayalamRepo;
import com.Aksharam.GuideApp.malayalam.subTittleMalayalam.SubTitleMalayalamRepo;
import com.Aksharam.GuideApp.malayalam.subTittleMalayalam.SubTittleMalayalam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/DataEntry")
@CrossOrigin
public class GuideAppController {
    @Autowired
    private GuideAppService guideAppService;        //dtId
    @Autowired

    private DataTypeRepo dataTypeRepo;
    @Autowired
    private MainTitleDataRepo mainTitleDataRepo;
    @Autowired
    private MainTittleMalayalamRepo malayalamRepo;
    @Autowired
    private SubTitleDataRepo subTitleDataRepo;
    @Autowired

    private SubTitleMalayalamRepo subTitleMalayalamRepo;   //,consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    @PostMapping(path = "/TalkType",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createCalendar(@RequestParam Integer dId,
                                            @RequestPart("data") DataEntryDTO dataEntryDTO,
                                            @RequestPart("img") MultipartFile img,
                                            @RequestPart("audio") MultipartFile audio,
                                            @RequestPart("video") MultipartFile video){
        try {
            Optional<DataType> dataTypeOptional = dataTypeRepo.findById(dId);
            if (dataTypeOptional.isPresent()){
                DataType dataType = dataTypeOptional.get();
                String tData = dataType.getTalk();
                if (tData!=null && "English".equalsIgnoreCase(tData)){
                    return guideAppService.storeEnglish(dataEntryDTO, img,audio,video);
                }else if (tData!=null && "Malayalam".equalsIgnoreCase(tData)){
                    return guideAppService.storeMalayalam(dataEntryDTO,img,audio,video);
                }else {
                    return new ResponseEntity<>("Language is not present", HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity<>("Language is not present",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){

            e.printStackTrace();
        }
        return new ResponseEntity<>("An unexpected error occurred ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/firstSub")
    public ResponseEntity<?>createFirstSub(@RequestParam String mId,
                                           @RequestPart("data") DataEntryDTO dataEntryDTO,
                                           @RequestPart("img") MultipartFile img,
                                           @RequestPart("audio") MultipartFile audio,
                                           @RequestPart("video") MultipartFile video){
        try {
            Optional<MainTitleData> mainTitleData = mainTitleDataRepo.findByuId(mId);
            Optional<MainTittleMalayalam> mainTmal = malayalamRepo.findByuId(mId);

            if (mainTitleData.isPresent()){
                MainTitleData english = mainTitleData.get();
                String mUid = english.getUId();
                return guideAppService.firstEngSubTitle(mUid,dataEntryDTO,img,audio,video);
            } else if (mainTmal.isPresent()) {
                MainTittleMalayalam malayalam = mainTmal.get();
                String mUid = malayalam.getUId();
                return guideAppService.firstMalSubTitle(mUid,dataEntryDTO,img,audio,video);
            }else {
                return new ResponseEntity<>("uId is not present",HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went to wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/secondSub")
    public ResponseEntity<?> createSecondSubTitle(@RequestParam String stId,
                                                  @RequestPart("data") DataEntryDTO dataEntryDTO,
                                                  @RequestPart("img") MultipartFile img,
                                                  @RequestPart("audio") MultipartFile audio,
                                                  @RequestPart("video") MultipartFile video){
        try {
            Optional<SubTitleData> sTData = subTitleDataRepo.findBysuId(stId);
            Optional<SubTittleMalayalam> stMalayalamData = subTitleMalayalamRepo.findBysuId(stId);

            if (sTData.isPresent()){
                return guideAppService.secondSubTitle(stId,dataEntryDTO,img,audio,video);
            }
            else if (stMalayalamData.isPresent()){
                return guideAppService.secondSubTitleMalayalam(stId,dataEntryDTO,img,audio,video);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went to wrong",HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(path = "/getAllMainList")
    public ResponseEntity<List<GetDataDTO>>getAllMainHeading(@RequestParam Integer dtId){
        try {
            Optional<DataType> dataTypeOptional = dataTypeRepo.findById(dtId);
            if (dataTypeOptional.isPresent()){
                DataType dataType = dataTypeOptional.get();
                String tData = dataType.getTalk();
                if (tData!=null && "English".equalsIgnoreCase(tData)){
                    return guideAppService.getAllMainHeading();
                }else if (tData!=null && "Malayalam".equalsIgnoreCase(tData)){
                    return guideAppService.getAllMainHeadingMalayalam();
                }else {
                    return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/getAllFirstSubList")
    public ResponseEntity<List<GetDataSubDto>>getAllFirstSubList(@RequestParam Integer dtId){
        try {
            Optional<DataType> dataTypeOptional = dataTypeRepo.findById(dtId);
            if (dataTypeOptional.isPresent()){
                DataType dataType = dataTypeOptional.get();
                String tData = dataType.getTalk();
                if (tData!=null && "English".equalsIgnoreCase(tData)){
                    return guideAppService.getAllFirstSub();
                }else if (tData!=null && "Malayalam".equalsIgnoreCase(tData)){
                    return guideAppService.getAllFirstSubMalayalam();
                }else {
                    return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/getAllSecondSubList")
    public ResponseEntity<List<GetDataSDTO>>getAllSecondSubList(@RequestParam Integer dtId){
        try {
            Optional<DataType> dataTypeOptional = dataTypeRepo.findById(dtId);
            if (dataTypeOptional.isPresent()){
                DataType dataType = dataTypeOptional.get();
                String tData = dataType.getTalk();
                if (tData!=null && "English".equalsIgnoreCase(tData)){
                    return guideAppService.getAllSecondSub();
                }else if (tData!=null && "Malayalam".equalsIgnoreCase(tData)){
                    return guideAppService.getAllSecondSubMalayalam();
                }else {
                    return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
