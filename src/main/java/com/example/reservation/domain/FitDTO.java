package com.example.reservation.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FitDTO extends CommonDTO{
    private Long num;  // 체육관 번호
    private String name;  // 체육관 이름
    private String post;  // 우편번호
    private String addr;  // 기본주소
    private String addrDetail;  // 상세주소
    private String indoorOutdoor;  // 실내/외 여부
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openTime;  // 오픈 시간
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime closeTime;  // 닫는 시간
    private String description;  // 설명
    private String facilityTypesStr;  // 편의시설 ','로 구분
    private List<String> facilityTypes;  // 체크박스로 받을 편의시설 목록
    private String latitude;
    private String longitude;
    
    private int fileAttached;  // 이미지 첨부 여부
    private MultipartFile fitMainFile; // 메인이미지 파일
    private List<MultipartFile> fitSubFile;  // 서브이미지 파일
    private String mainFilePath;
    private List<String> subFilePath;
}