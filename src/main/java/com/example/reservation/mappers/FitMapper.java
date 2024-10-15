package com.example.reservation.mappers;

import com.example.reservation.domain.FitDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;
import java.util.Map;

@Mapper
public interface FitMapper {

    // 체육시설 정보 등록
    // @Options : 쿼리 실행 후 기본키 반환
    @Options(useGeneratedKeys = true, keyProperty = "num")
    public int insertFit(FitDTO fitDTO);

    // 체육시설에 있는 편의시설 등록
    public int insertFacility(Map<String, Object> params);

    // 체육시설, 편의시설 조회
    public FitDTO selectFit(Long num);

    // 체육시설 수정
    public int updateFit(FitDTO fitDTO);

    public int deleteFacility(Long num);

    // 체육시설 삭제
    public int deleteFit(Long num);

    // 체육시설 리스트
    public List<FitDTO> listFit();

    public int cntFit();

}
