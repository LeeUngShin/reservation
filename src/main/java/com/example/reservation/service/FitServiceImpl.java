package com.example.reservation.service;

import com.example.reservation.domain.FitDTO;
import com.example.reservation.mappers.FitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class FitServiceImpl implements FitService{

    @Autowired
    private FitMapper fitMapper;

    @Override
    public boolean registerFit(FitDTO fitDTO) throws DataAccessException{

        int result = 0;
        Long fitNum = 0L;
        if(fitDTO.getNum() == null) {
            result = fitMapper.insertFit(fitDTO);
            fitNum = fitDTO.getNum();
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
    public List<FitDTO> searchListFit(String bigRegion, String smallRegion, String typeChoice) {
        List<FitDTO> list = fitMapper.listFit();
        System.out.println(list);
        return list;
    }
}