package com.example.reservation.mappers;

import com.example.reservation.domain.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

    // 예약정보 등록
    public int insertReservation(ReservationDTO reservationDTO);
}
