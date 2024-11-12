package com.example.reservation.controller;

import com.example.reservation.constant.Method;
import com.example.reservation.domain.FitDTO;
import com.example.reservation.domain.ReservationDTO;
import com.example.reservation.service.FitService;
import com.example.reservation.service.ReservationService;
import com.example.reservation.utils.UiUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;


import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
        Map<String, Object> map = new HashMap<>();
        map.put("num", fitDTO.getNum());
        map.put("day", formattedDate);
        List<Time> timeList = reservationService.reservationTimeInfo(map);
        model.addAttribute("timeList", timeList);
        List<String> listTest = new ArrayList<>();
        listTest.add("aa");
        listTest.add("bb");
        model.addAttribute("data", listTest);
        ReservationDTO reservationDTO = new ReservationDTO();
        model.addAttribute("reservationDTO", reservationDTO);

        return "reservation/reservation";
    }


    @GetMapping("/selectDay")
    @ResponseBody
    public String selectDay(@RequestParam("gymNum") String num,
                        @RequestParam("selectedDay") String selectedDay){
        System.out.println("짐번호 : " + num);
        System.out.println("선택 날짜 : " + selectedDay);
        Map map = new HashMap();
        map.put("num", num);
        map.put("day", selectedDay);
        List<Time> list = new ArrayList<>();
        list = reservationService.reservationTimeInfo(map);
        String json = new Gson().toJson(list);
        return json;
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