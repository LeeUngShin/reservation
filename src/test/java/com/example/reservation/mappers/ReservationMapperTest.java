package com.example.reservation.mappers;

import com.example.reservation.domain.ReservationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReservationMapperTest {

    @Autowired
    private ReservationMapper reservationMapper;

    @Test
    void insertReservation() {

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setNum(1L);
        reservationDTO.setUserNum(10L);
        reservationDTO.setGymNum(10L);
        reservationDTO.setReservationDate(LocalDate.parse("2024-11-01"));
        reservationDTO.setReservationStartTime(LocalTime.parse("13:00"));
        reservationDTO.setReservationEndTime(reservationDTO.getReservationStartTime().plusHours(1));
        reservationDTO.setPersonCnt(5);

        int result = reservationMapper.insertReservation(reservationDTO);
        if(result==1){
            System.out.println("성공");
        }
        
    }

    @Test
    void reservationInfo() {

        List<Time> timeList = reservationMapper.reservationTimeInfo(10L);
        System.out.println("---------------------");
        System.out.println(timeList);
        System.out.println("---------------------");
    }
}