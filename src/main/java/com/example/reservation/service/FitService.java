package com.example.reservation.service;

import com.example.reservation.domain.FitDTO;

import java.util.List;

public interface FitService {

    public boolean registerFit(FitDTO fitDTO);

    public void registerFacility(Long num, List<String> facility);

    public boolean deleteFit(Long num);

    public List<FitDTO> listFit();

    public FitDTO fitInfo(Long num);

    public List<FitDTO> searchListFit(String bigRegion, String smallRegion, String typeChoice);
}
