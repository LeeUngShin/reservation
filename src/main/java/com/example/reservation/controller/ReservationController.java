package com.example.reservation.controller;

import com.example.reservation.constant.Method;
import com.example.reservation.domain.FitDTO;
import com.example.reservation.domain.ReservationDTO;
import com.example.reservation.service.FitService;
import com.example.reservation.service.ReservationService;
import com.example.reservation.utils.UiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController extends UiUtils {

    @Autowired
    private FitService fitService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/{num}")
    public String reservationForm(@PathVariable(value = "num") Long num,
                        Model model){
        FitDTO fitDTO = fitService.fitInfo(num);
        System.out.println("가져온 fitDTO : " + fitDTO);
        model.addAttribute("fitDTO", fitDTO);
        List<Time> timeList = reservationService.reservationTimeInfo(num);
        model.addAttribute("timeList", timeList);
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
        try{
            boolean insertResult = reservationService.reservationRegister(reservationDTO);
            if(insertResult==false)
                return showMessageWithRedirect("예약에 실패했습니다..","/home", Method.GET, null, model);
        }catch (DataAccessException e){
            e.printStackTrace();
            return showMessageWithRedirect("데이터 처리과정 중 문제가 발생했습니다.","/home", Method.GET, null, model);
        }catch (Exception e){
            e.printStackTrace();
            return showMessageWithRedirect("시스템 에러가 발생했습니다.","/home", Method.GET, null, model);
        }
        return showMessageWithRedirect("예약이 완료되었습니다.","/home", Method.GET, null, model);
    }
}