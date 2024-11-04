package com.example.reservation.mappers;

import com.example.reservation.domain.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Time;
import java.util.List;

@Mapper
public interface ReservationMapper {

    // 예약정보 등록
    public int insertReservation(ReservationDTO reservationDTO);

    // 예약시간정보
    public List<Time> reservationTimeInfo(Long gymNum);
}