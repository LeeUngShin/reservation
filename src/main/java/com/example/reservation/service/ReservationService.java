package com.example.reservation.service;

import com.example.reservation.domain.ReservationDTO;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public interface ReservationService {

    public boolean reservationRegister(ReservationDTO reservationDTO);

    public List<Time> reservationTimeInfo(Map map);
}
