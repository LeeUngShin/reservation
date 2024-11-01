package com.example.reservation.mappers;

import com.example.reservation.domain.FitDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FitMapperTest {

    @Autowired
    private FitMapper fitMapper;

    @Test
    void insertFit() {

        String fType1 = "주차장";
        String fType2 = "샤워실";
        String fType3 = "라커룸";
        String fType4 = "wifi";
        String fType5 = "냉난방시설";

        LocalTime openTime = LocalTime.now();
        LocalTime closeTime = LocalTime.now().plusHours(3);
        List<String> facilityTypes = new ArrayList<>();
        //facilityTypes.add(fType1);
        //facilityTypes.add(fType2);
        //facilityTypes.add(fType3);
        //facilityTypes.add(fType4);
        //facilityTypes.add(fType5);

        FitDTO fitDTO = new FitDTO();
        fitDTO.setName("체육관06");
        fitDTO.setName("체육관이름06");
        fitDTO.setPost("체육관우편번호06");
        fitDTO.setAddr("체육관기본주소06");
        fitDTO.setAddrDetail("체육관상세주소06");
        fitDTO.setIndoorOutdoor("실외");
        fitDTO.setOpenTime(openTime);
        fitDTO.setCloseTime(closeTime);
        fitDTO.setDescription("체육관설명06");
        fitDTO.setFacilityTypes(facilityTypes);
        int result1 = fitMapper.insertFit(fitDTO);
        System.out.println("fit 등록 결과 : " + result1);
        if (result1 == 1) {
            Long fitNum = fitDTO.getNum();
            System.out.println("등록한 번호 : " + fitNum);
            System.out.println("등록할 객체 : " + fitDTO);
            for (String facility : facilityTypes) {
                System.out.println("등록할 리스트 내용 : " + facility);
                HashMap<String, Object> map = new HashMap<>();
                map.put("num", fitNum);
                map.put("facility", facility);
                fitMapper.insertFacility(map);
            }
        }
    }

    @Test
    void selectFit() {
        FitDTO fitDTO = fitMapper.selectFit((long) 14);
        System.out.println("db에서 select 데이터1 : " + fitDTO);
        if (fitDTO.getFacilityTypesStr() != null) {
            fitDTO.setFacilityTypes(Arrays.asList(fitDTO.getFacilityTypesStr().split(",")));
        }
        System.out.println("db에서 select 데이터2 : " + fitDTO);
    }

    @Test
    void updateFit() {
        String fType1 = "주차장";
        String fType2 = "샤워실";
        String fType3 = "라커룸";
        String fType4 = "wifi";
        String fType5 = "냉난방시설";

        LocalTime openTime = LocalTime.now();
        LocalTime closeTime = LocalTime.now().plusHours(3);
        List<String> facilityTypes = new ArrayList<>();
        facilityTypes.add(fType1);
        //facilityTypes.add(fType2);
        facilityTypes.add(fType3);
        //facilityTypes.add(fType4);
        //facilityTypes.add(fType5);

        FitDTO fitDTO = new FitDTO();
        fitDTO.setNum((long) 9);
        fitDTO.setName("체육관01(수정)");
        fitDTO.setName("체육관이름01(수정)");
        fitDTO.setPost("체육관우편번호01(수정)");
        fitDTO.setAddr("체육관기본주소01수정)");
        fitDTO.setAddrDetail("체육관상세주소01(수정)");
        fitDTO.setIndoorOutdoor("실외");
        fitDTO.setOpenTime(openTime);
        fitDTO.setCloseTime(closeTime);
        fitDTO.setDescription("체육관설명01(수정)");
        fitDTO.setFacilityTypes(facilityTypes);
        fitMapper.updateFit(fitDTO);
        Long updateNum = fitDTO.getNum();
        fitMapper.deleteFacility(updateNum);
        for(String facility : fitDTO.getFacilityTypes()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("num", updateNum);
            map.put("facility", facility);
            fitMapper.insertFacility(map);
        }
        FitDTO udpatedFitDTO = fitMapper.selectFit((long) 9);
        if (udpatedFitDTO.getFacilityTypesStr() != null) {
            udpatedFitDTO.setFacilityTypes(Arrays.asList(udpatedFitDTO.getFacilityTypesStr().split(",")));
        }
        System.out.println("수정 후 데이터 : " + udpatedFitDTO);
    }

    @Test
    void deleteFit() {
        fitMapper.deleteFit((long) 9);
        //fitMapper.deleteFacility((long) 9);
        System.out.println("삭제 후 데이터 : " + fitMapper.selectFit((long) 1));

    }

    @Test
    void listFit() {
        int cntFit = fitMapper.cntFit();
        System.out.println("전체 gym 개수 : " + cntFit);
        if(cntFit>0){
            List<FitDTO> fitDTOList = fitMapper.listFit();
            System.out.println("리스트 개수 : " + fitDTOList.size());
            if(fitDTOList!=null){
                for(FitDTO fitDTO : fitDTOList){
                    if(fitDTO.getFacilityTypesStr() != null) {
                        fitDTO.setFacilityTypes(Arrays.asList(fitDTO.getFacilityTypesStr().split(",")));
                    }
                    System.out.println("리스트 하나씩 가져옴 : " + fitDTO);
                }
            }
        }
    }

    @Test
    void searchListFit() {
        String bigRegion = "기본";
        String smallRegion = "주소";
        String typeChoice = "실내";
        Map map = new HashMap();
        map.put("bigRegion", bigRegion);
        map.put("smallRegion", smallRegion);
        map.put("typeChoice", typeChoice);
        List<FitDTO> searchListFit = fitMapper.searchListFit(map);
        System.out.println(searchListFit);
    }
}