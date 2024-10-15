package com.example.reservation.service;

import com.example.reservation.domain.FitDTO;

public interface FitService {

    public boolean registerFit(FitDTO fitDTO);

    public boolean registerFacility(Long num, String facility);
}
