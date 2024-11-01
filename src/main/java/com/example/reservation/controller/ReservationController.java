package com.example.reservation.controller;

import com.example.reservation.domain.FitDTO;
import com.example.reservation.domain.ReservationDTO;
import com.example.reservation.service.FitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private FitService fitService;

    @GetMapping("/{num}")
    public String reservationForm(@PathVariable(value = "num") Long num,
                        Model model){
        FitDTO fitDTO = fitService.fitInfo(num);
        System.out.println("가져온 fitDTO : " + fitDTO);
        model.addAttribute("fitDTO", fitDTO);
        List<String> listTest = new ArrayList<>();
        listTest.add("aa");
        listTest.add("bb");
        model.addAttribute("data", listTest);
        ReservationDTO reservationDTO = new ReservationDTO();
        model.addAttribute("reservationDTO", reservationDTO);

        return "reservation/reservation";
    }

    @PostMapping("/reservationSubmit")
    public String reservationSubmit(@ModelAttribute ReservationDTO reservationDTO,
                                    Model model){

        System.out.println("예약 정보 : " + reservationDTO);
        return "main";
    }

}