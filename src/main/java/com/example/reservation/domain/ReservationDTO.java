package com.example.reservation.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO extends CommonDTO{

    private Long num;

    private Long userNum;

    private Long gymNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDate;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime reservationStartTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime reservationEndTime;

    private int personCnt;

}
