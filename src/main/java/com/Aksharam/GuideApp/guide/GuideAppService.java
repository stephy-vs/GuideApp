package com.Aksharam.GuideApp.guide;

import com.Aksharam.GuideApp.dto.DataEntryDTO;
import com.Aksharam.GuideApp.dto.GetDataDTO;
import com.Aksharam.GuideApp.dto.GetDataSDTO;
import com.Aksharam.GuideApp.dto.GetDataSubDto;
import com.Aksharam.GuideApp.english.SubTitle.SubTitleData;
import com.Aksharam.GuideApp.english.SubTitle.SubTitleDataRepo;
import com.Aksharam.GuideApp.english.mainTitle.MainTitleData;
import com.Aksharam.GuideApp.english.mainTitle.MainTitleDataRepo;
import com.Aksharam.GuideApp.english.secondSub.SecondSubData;
import com.Aksharam.GuideApp.english.secondSub.SecondSubRepo;
import com.Aksharam.GuideApp.file.StorageRepo;
import com.Aksharam.GuideApp.file.StorageService;
import com.Aksharam.GuideApp.malayalam.mainTitle.MainTittleMalayalam;
import com.Aksharam.GuideApp.malayalam.mainTitle.MainTittleMalayalamRepo;
import com.Aksharam.GuideApp.malayalam.secondSubMalayalam.SecondSubMalayalamData;
import com.Aksharam.GuideApp.malayalam.secondSubMalayalam.SecondSubMalayalamRepo;
import com.Aksharam.GuideApp.malayalam.subTittleMalayalam.SubTitleMalayalamRepo;
import com.Aksharam.GuideApp.malayalam.subTittleMalayalam.SubTittleMalayalam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuideAppService {
    @Autowired
    private AlphaNumeric alphaNumeric;

    @Autowired
    private StorageRepo storageRepo;
    @Autowired
    private StorageService storageService;

    @Autowired
    private MainTitleDataRepo mainTitleDataRepo;

    @Autowired
    private MainTittleMalayalamRepo tittleMalayalamRepo;

    @Autowired
    private SubTitleDataRepo subTitleDataRepo;

    @Autowired
    private SubTitleMalayalamRepo subTitleMalayalamRepo;

    @Autowired
    private SecondSubRepo secondSubRepo;

    @Autowired
    private SecondSubMalayalamRepo secondSubMalayalamRepo;

    public ResponseEntity<?> storeEnglish(DataEntryDTO dataEntryDTO, MultipartFile img,
                                          MultipartFile audio, MultipartFile video) {
        try {

            MainTitleData mData = new MainTitleData();
            String rNum = alphaNumeric.generateRandomNumber();
            mData.setUId(rNum);

            mData.setTitle(dataEntryDTO.getTitle());
            mData.setDescription(dataEntryDTO.getDescription());

            String imgResult = storageService.uploadFile(img);
            String[] parts = imgResult.split(";");
            String imgNameR = parts[0];
            String imgUrlR = parts[1];
            mData.setImgUrl(imgUrlR);
            mData.setImgName(imgNameR);

            String mp3 = storageService.uploadFile(audio);
            String[] parts1 = mp3.split(";");
            String mp3Name = parts1[0];
            String mp3Url = parts1[1];
            mData.setAudioUrl(mp3Url);
            mData.setAudioName(mp3Name);

            String mp4 = storageService.uploadFile(video);
            String[] parts2 = mp4.split(";");
            String mp4Name = parts2[0];
            String mp4Url = parts2[1];
            mData.setVideoName(mp4Name);
            mData.setVideoUrl(mp4Url);

            mData.setReferenceUrl(dataEntryDTO.getReferenceUrl());
            mainTitleDataRepo.save(mData);
            return new ResponseEntity<>(mData, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went to wrong!!!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> storeMalayalam(DataEntryDTO dataEntryDTO, MultipartFile img,
                                            MultipartFile audio,MultipartFile video) {
        try {
            MainTittleMalayalam mtData = new MainTittleMalayalam();
            String rNum = alphaNumeric.generateRandomNumber();
            mtData.setUId(rNum);
            mtData.setDescription(dataEntryDTO.getDescription());

            mtData.setTitle(dataEntryDTO.getTitle());

            String imgResult = storageService.uploadFile(img);
            String[] parts = imgResult.split(";");
            String imgNameR = parts[0];
            String imgUrlR = parts[1];
            mtData.setImgName(imgNameR);
            mtData.setImgUrl(imgUrlR);

            String mp3 = storageService.uploadFile(audio);
            String[] parts1 = mp3.split(";");
            String mp3Name = parts1[0];
            String mp3Url = parts1[1];
            mtData.setAudioName(mp3Name);
            mtData.setAudioUrl(mp3Url);

            String mp4 = storageService.uploadFile(video);
            String[] parts2 = mp4.split(";");
            String mp4Name = parts2[0];
            String mp4Url = parts2[1];
            mtData.setVideoName(mp4Name);
            mtData.setVideoUrl(mp4Url);

            mtData.setReferenceUrl(dataEntryDTO.getReferenceUrl());
            tittleMalayalamRepo.save(mtData);
            return new ResponseEntity<>(mtData,HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went to Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> firstEngSubTitle(String mUid, DataEntryDTO dataEntryDTO,
                                              MultipartFile img, MultipartFile audio, MultipartFile video) {
        try {
            String suId = alphaNumeric.generateRandomNumber();
            SubTitleData subTData = new SubTitleData();
            subTData.setSuId(suId);
            subTData.setMainHId(mUid);
            subTData.setTitle(dataEntryDTO.getTitle());
            subTData.setDescription(dataEntryDTO.getDescription());

            String imgResult = storageService.uploadFile(img);
            String[] parts = imgResult.split(";");
            String imgNameR = parts[0];
            String imgUrlR = parts[1];
            subTData.setImgUrl(imgUrlR);
            subTData.setImgName(imgNameR);

            String mp3 = storageService.uploadFile(audio);
            String[] parts1 = mp3.split(";");
            String mp3Name = parts1[0];
            String mp3Url = parts1[1];
            subTData.setAudioName(mp3Name);
            subTData.setAudioUrl(mp3Url);

            String mp4 = storageService.uploadFile(video);
            String[] parts2 = mp4.split(";");
            String mp4Name = parts2[0];
            String mp4Url = parts2[1];
            subTData.setVideoName(mp4Name);
            subTData.setVideoUrl(mp4Url);

            subTData.setReferenceUrl(dataEntryDTO.getReferenceUrl());
            subTitleDataRepo.save(subTData);
            return new ResponseEntity<>(subTData,HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went to wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> firstMalSubTitle(String mUid, DataEntryDTO dataEntryDTO,
                                              MultipartFile img, MultipartFile audio, MultipartFile video) {
        try {
            SubTittleMalayalam malayalam = new SubTittleMalayalam();
            malayalam.setTitle(dataEntryDTO.getTitle());
            String smuid = alphaNumeric.generateRandomNumber();
            malayalam.setSuId(smuid);
            malayalam.setDescription(dataEntryDTO.getDescription());

            String imgResult = storageService.uploadFile(img);
            String[] parts = imgResult.split(";");
            String imgNameR = parts[0];
            String imgUrlR = parts[1];
            malayalam.setImgUrl(imgUrlR);
            malayalam.setImgName(imgNameR);

            String mp3 = storageService.uploadFile(audio);
            String[] parts1 = mp3.split(";");
            String mp3Name = parts1[0];
            String mp3Url = parts1[1];
            malayalam.setAudioName(mp3Name);
            malayalam.setAudioUrl(mp3Url);

            String mp4 = storageService.uploadFile(video);
            String[] parts2 = mp4.split(";");
            String mp4Name = parts2[0];
            String mp4Url = parts2[1];
            malayalam.setVideoName(mp4Name);
            malayalam.setVideoUrl(mp4Url);

            malayalam.setReferenceUrl(dataEntryDTO.getReferenceUrl());
            malayalam.setMainHid(mUid);
            subTitleMalayalamRepo.save(malayalam);
            return new ResponseEntity<>(malayalam,HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went to wrong.",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> secondSubTitle(String stId, DataEntryDTO dataEntryDTO,
                                            MultipartFile img, MultipartFile audio, MultipartFile video) {
        try {
            SecondSubData secondSubData = new SecondSubData();
            secondSubData.setFSubId(stId);
            secondSubData.setTitle(dataEntryDTO.getTitle());
            String subSid = alphaNumeric.generateRandomNumber();
            secondSubData.setSSubId(subSid);
            secondSubData.setDescription(dataEntryDTO.getDescription());

            String imgResult = storageService.uploadFile(img);
            String[] parts = imgResult.split(";");
            String imgNameR = parts[0];
            String imgUrlR = parts[1];
            secondSubData.setImgName(imgNameR);
            secondSubData.setImgUrl(imgUrlR);

            String mp3 = storageService.uploadFile(audio);
            String[] parts1 = mp3.split(";");
            String mp3Name = parts1[0];
            String mp3Url = parts1[1];
            secondSubData.setAudioName(mp3Name);
            secondSubData.setAudioUrl(mp3Url);

            String mp4 = storageService.uploadFile(video);
            String[] parts2 = mp4.split(";");
            String mp4Name = parts2[0];
            String mp4Url = parts2[1];
            secondSubData.setVideoName(mp4Name);
            secondSubData.setVideoUrl(mp4Url);

            secondSubData.setReferenceUrl(dataEntryDTO.getReferenceUrl());
            secondSubRepo.save(secondSubData);
            return new ResponseEntity<>(secondSubData,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went to wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> secondSubTitleMalayalam(String stId, DataEntryDTO dataEntryDTO, MultipartFile img,
                                                     MultipartFile audio, MultipartFile video) {
        try {
            SecondSubMalayalamData subMalayalamData = new SecondSubMalayalamData();
            subMalayalamData.setFSubId(stId);
            subMalayalamData.setTitle(dataEntryDTO.getTitle());
            String sUid = alphaNumeric.generateRandomNumber();
            subMalayalamData.setSSubId(sUid);
            subMalayalamData.setDescription(dataEntryDTO.getDescription());

            String imgResult = storageService.uploadFile(img);
            String[] parts = imgResult.split(";");
            String imgNameR = parts[0];
            String imgUrlR = parts[1];
            subMalayalamData.setImgName(imgNameR);
            subMalayalamData.setImgUrl(imgUrlR);

            String mp3 = storageService.uploadFile(audio);
            String[] parts1 = mp3.split(";");
            String mp3Name = parts1[0];
            String mp3Url = parts1[1];
            subMalayalamData.setAudioName(mp3Name);
            subMalayalamData.setAudioUrl(mp3Url);

            String mp4 = storageService.uploadFile(video);
            String[] parts2 = mp4.split(";");
            String mp4Name = parts2[0];
            String mp4Url = parts2[1];
            subMalayalamData.setVideoName(mp4Name);
            subMalayalamData.setVideoUrl(mp4Url);
            secondSubMalayalamRepo.save(subMalayalamData);
            return new ResponseEntity<>(subMalayalamData,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went to wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<GetDataDTO>> getAllMainHeading() {
        try {
            List<GetDataDTO> dtoList = mainTitleDataRepo.findAll()
                    .stream().map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(dtoList,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private GetDataDTO convertToDTO(MainTitleData mainTitleData) {
        return new GetDataDTO(mainTitleData.getTitle(),mainTitleData.getUId(),mainTitleData.getDescription(),
                mainTitleData.getImgUrl(),mainTitleData.getImgName(),mainTitleData.getAudioUrl(),
                mainTitleData.getAudioName(),mainTitleData.getVideoUrl(),mainTitleData.getVideoName(),
                mainTitleData.getReferenceUrl()
        );
    }

    public ResponseEntity<List<GetDataDTO>> getAllMainHeadingMalayalam() {
        try {
            List<GetDataDTO> getDataDTOS = tittleMalayalamRepo.findAll()
                    .stream().map(this::convertMainMalayalamToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(getDataDTOS,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private GetDataDTO convertMainMalayalamToDTO(MainTittleMalayalam mainTittleMalayalam) {
        return new GetDataDTO(mainTittleMalayalam.getTitle(),mainTittleMalayalam.getUId(),mainTittleMalayalam.getDescription(),
                mainTittleMalayalam.getImgUrl(),mainTittleMalayalam.getImgName(),mainTittleMalayalam.getAudioUrl(),
                mainTittleMalayalam.getAudioName(),mainTittleMalayalam.getVideoUrl(),mainTittleMalayalam.getVideoName(),
                mainTittleMalayalam.getReferenceUrl());
    }

    public ResponseEntity<List<GetDataSubDto>> getAllFirstSub() {
        try {
            List<GetDataSubDto> getDataDTOS = subTitleDataRepo.findAll()
                    .stream().map(this::convertFirstSubEnglishToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(getDataDTOS,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private GetDataSubDto convertFirstSubEnglishToDTO(SubTitleData subTitleData) {
        return new GetDataSubDto(subTitleData.getTitle(),subTitleData.getSuId(),subTitleData.getDescription(),
                subTitleData.getImgUrl(),subTitleData.getImgName(),subTitleData.getAudioUrl(),
                subTitleData.getAudioName(),subTitleData.getVideoUrl(),subTitleData.getVideoName(),
                subTitleData.getReferenceUrl()
        );
    }

    public ResponseEntity<List<GetDataSubDto>> getAllFirstSubMalayalam() {
        try {
            List<GetDataSubDto> getDataDTOS = subTitleMalayalamRepo.findAll()
                    .stream().map(this::convertFirstSubMalayalamToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(getDataDTOS,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private GetDataSubDto convertFirstSubMalayalamToDTO(SubTittleMalayalam subTittleMalayalam) {
        return new GetDataSubDto(subTittleMalayalam.getTitle(),subTittleMalayalam.getSuId(),subTittleMalayalam.getDescription(),
                subTittleMalayalam.getImgUrl(),subTittleMalayalam.getImgName(),subTittleMalayalam.getAudioUrl(),
                subTittleMalayalam.getAudioName(),subTittleMalayalam.getVideoUrl(),subTittleMalayalam.getVideoName(),
                subTittleMalayalam.getReferenceUrl()
        );
    }

    public ResponseEntity<List<GetDataSDTO>> getAllSecondSub() {
        try {
            List<GetDataSDTO> getDataDTOS = secondSubRepo.findAll()
                    .stream().map(this::convertSecondSubEnglishToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(getDataDTOS,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private GetDataSDTO convertSecondSubEnglishToDTO(SecondSubData secondSubData) {
        return new GetDataSDTO(secondSubData.getTitle(),secondSubData.getSSubId(),secondSubData.getDescription(),
                secondSubData.getImgUrl(),secondSubData.getImgName(),secondSubData.getAudioUrl(),
                secondSubData.getAudioName(),secondSubData.getVideoUrl(),secondSubData.getVideoName(),
                secondSubData.getReferenceUrl()
        );
    }

    public ResponseEntity<List<GetDataSDTO>> getAllSecondSubMalayalam() {
        try {
            List<GetDataSDTO> getDataDTOS = secondSubMalayalamRepo.findAll()
                    .stream().map(this::convertSecondSubMalayalamToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(getDataDTOS,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private GetDataSDTO convertSecondSubMalayalamToDTO(SecondSubMalayalamData secondSubMalayalamData) {
        return new GetDataSDTO(secondSubMalayalamData.getTitle(),secondSubMalayalamData.getSSubId(),secondSubMalayalamData.getDescription(),
                secondSubMalayalamData.getImgUrl(),secondSubMalayalamData.getImgName(),secondSubMalayalamData.getAudioUrl(),
                secondSubMalayalamData.getAudioName(),secondSubMalayalamData.getVideoUrl(),secondSubMalayalamData.getVideoName(),
                secondSubMalayalamData.getReferenceUrl()
        );
    }
}
