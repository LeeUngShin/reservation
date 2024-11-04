package com.example.reservation.service;

import com.example.reservation.domain.ReservationDTO;

import java.sql.Time;
import java.util.List;

public interface ReservationService {

    public boolean reservationRegister(ReservationDTO reservationDTO);

    public List<Time> reservationTimeInfo(Long gymNum);
}
