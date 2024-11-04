package com.example.reservation.service;

import com.example.reservation.domain.ReservationDTO;
import com.example.reservation.mappers.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class ReservationImpl implements ReservationService{

    @Autowired
    private ReservationMapper reservationMapper;
    @Override
    public boolean reservationRegister(ReservationDTO reservationDTO) {

        int result = reservationMapper.insertReservation(reservationDTO);
        if(result==1){
            return true;
        }
        return false;
    }

    @Override
    public List<Time> reservationTimeInfo(Long gymNum) {

        List<Time> timeList = reservationMapper.reservationTimeInfo(gymNum);

        return timeList;
    }


}
