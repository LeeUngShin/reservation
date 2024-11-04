package com.example.reservation.service;

import com.example.reservation.domain.FitDTO;
import com.example.reservation.domain.FitFileDTO;
import com.example.reservation.mappers.FitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class FitServiceImpl implements FitService{

    @Autowired
    private FitMapper fitMapper;

    @Override
    public boolean registerFit(FitDTO fitDTO) throws DataAccessException, IOException {

        int result = 0;
        Long fitNum = 0L;
        Long mainFileNum = 0L;
        if(fitDTO.getNum() == null) {
            if(fitDTO.getFitMainFile().isEmpty() || fitDTO.getFitSubFile().get(0).isEmpty()){  // 첨부파일 없이 등록하는 경우
                fitDTO.setFileAttached(0);  // fileAttached값 0으로 설정
                result = fitMapper.insertFit(fitDTO);
                fitNum = fitDTO.getNum();
                System.out.println("저장한 핏 기본키 : " + fitNum);
            }else{  // 첨부파일이 있이 등록하는 경우
                fitDTO.setFileAttached(1);  // fileAttached 1로 설정
                result = fitMapper.insertFit(fitDTO);
                fitNum = fitDTO.getNum();  // 저장한 fit행의 기본키
                System.out.println("저장한 핏 기본키 : " + fitNum);
                MultipartFile fitMainFile = fitDTO.getFitMainFile();
                String originalMainFileName = fitMainFile.getOriginalFilename();
                System.out.println("오리지날메인파일이름 : " + originalMainFileName);
                // 파일 저장용 이름
                System.out.println(System.currentTimeMillis());
                String storedMainFileName = System.currentTimeMillis() + "_" + originalMainFileName;
                System.out.println("저장용메일파일이름 : " + storedMainFileName);
                // FitFileDTO 객체 만들기
                FitFileDTO fitMainFileDTO = new FitFileDTO();
                fitMainFileDTO.setOriginalFileName(originalMainFileName);
                fitMainFileDTO.setStoredFileName(storedMainFileName);
                fitMainFileDTO.setFitNum(fitNum);
                // 파일 저장한 폴더
                String MainFileSavePath = "C:/uploads/" + storedMainFileName;
                // 위의 폴더에 파일 저장
                fitMainFile.transferTo(new File(MainFileSavePath));
                // DB에 파일 정보 저장
                fitMapper.insertFitMainFile(fitMainFileDTO);
                mainFileNum = fitMainFileDTO.getNum();
                System.out.println("저장한 메인파일 기본키 : " + mainFileNum);

                List<MultipartFile> fitSubFileList = fitDTO.getFitSubFile();  // 첨부한 파일객체
                // 파일 원본 이름
                // 첨부한 다중파일을 하나씩 fit_file 테이블에 저장
                for(MultipartFile fitSubFile : fitSubFileList) {
                    String originalSubFileName = fitSubFile.getOriginalFilename();
                    System.out.println("오리지날서브파일이름 : " + originalSubFileName);
                    // 파일 저장용 이름
                    System.out.println(System.currentTimeMillis());
                    String storedSubFileName = System.currentTimeMillis() + "_" + originalSubFileName;
                    System.out.println("저장서브용파일이름 : " + storedSubFileName);
                    // FitFileDTO 객체 만들기
                    FitFileDTO fitSubFileDTO = new FitFileDTO();
                    fitSubFileDTO.setOriginalFileName(originalSubFileName);
                    fitSubFileDTO.setStoredFileName(storedSubFileName);
                    fitSubFileDTO.setMainFileNum(mainFileNum);
                    // 파일 저장한 폴더
                    String SubFileSavePath = "C:/uploads/" + storedSubFileName;
                    // 위의 폴더에 파일 저장
                    fitSubFile.transferTo(new File(SubFileSavePath));
                    // DB에 파일 정보 저장
                    fitMapper.insertFitSubFile(fitSubFileDTO);
                }
            }

            System.out.println("이번에 저장한 fit 번호 : " + fitDTO.getNum());
            System.out.println("저장한 fit 결과 개수 : " + result);
        }else{
            result = fitMapper.updateFit(fitDTO);
            fitNum = fitDTO.getNum();
        }

        if(result==1){
            registerFacility(fitNum, fitDTO.getFacilityTypes());
            return true;
        }
        return false;
    }

    @Override
    public void registerFacility(Long num, List<String> facility) throws DataAccessException {

        HashMap<String, Object> params = new HashMap<>();
        int facilityCnt = 0;
        for(String facilityStr : facility){

            params.put("num", num);
            params.put("facility", facilityStr);
            fitMapper.insertFacility(params);
            facilityCnt++;

        }
        System.out.println("이번에 저장한 fit에 있는 편의시설 개수 : " + facilityCnt);
    }

    @Override
    public boolean deleteFit(Long num) {

        int result = 0;
        FitDTO fitDTO = fitMapper.selectFit(num);
        if(fitDTO != null || fitDTO.getDeleteYn().equals("N")){
            result = fitMapper.deleteFit(num);
        }
        if(result==1) return true;
        return false;
    }

    @Override
    public List<FitDTO> listFit() {

        List<FitDTO> list = fitMapper.listFit();
        System.out.println(list);

        return list;
    }


    @Override
    public FitDTO fitInfo(Long num) {

        FitDTO fitDTO = fitMapper.selectFit(num);
        String[] arrFacility = fitDTO.getFacilityTypesStr().split(",");
        System.out.println("----------------------");
        System.out.println(Arrays.toString(arrFacility));
        List<String> listFacility = Arrays.asList(arrFacility);
        System.out.println(listFacility);
        System.out.println("----------------------");
        fitDTO.setFacilityTypes(listFacility);
        return fitDTO;
    }

    @Override
    public FitFileDTO fitMainFileInfo(Long gymNum) {
        FitFileDTO fitFileDTO = fitMapper.selectFitMainFile(gymNum);
        return fitFileDTO;
    }


    @Override
    public List<FitFileDTO> fitSubFileInfo(Long mainFileNum) {
        List<FitFileDTO> fitFileDTOList = fitMapper.selectFitSubFile(mainFileNum);
        return fitFileDTOList;
    }

    @Override
    public List<FitDTO> searchListFit(String bigRegion, String smallRegion, String typeChoice) {
        List<FitDTO> list = fitMapper.listFit();
        System.out.println(list);
        return list;
    }
}