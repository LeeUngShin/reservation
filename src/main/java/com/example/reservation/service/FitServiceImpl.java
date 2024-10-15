package com.example.reservation.service;

import com.example.reservation.domain.FitDTO;
import org.springframework.stereotype.Service;

@Service
public class FitServiceImpl implements FitService{

    @Override
    public boolean registerFit(FitDTO fitDTO) {
        return false;
    }

    @Override
    public boolean registerFacility(Long num, String facility) {
        return false;
    }
}
