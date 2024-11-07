package com.example.reservation.service;

import com.example.reservation.domain.FitDTO;
import com.example.reservation.domain.FitFileDTO;

import java.io.IOException;
import java.util.List;

public interface FitService {

    public boolean registerFit(FitDTO fitDTO) throws IOException;

    public void registerFacility(Long num, List<String> facility);

    public boolean deleteFit(Long num);

    public List<FitDTO> listFit(FitDTO fitDTO);

    public FitDTO fitInfo(Long num);

    public List<FitFileDTO> fitSubFileInfo(Long num);

    public FitFileDTO fitMainFileInfo(Long mainFileNum);

    public List<FitDTO> searchListFit(String bigRegion, String smallRegion, String typeChoice);
}
