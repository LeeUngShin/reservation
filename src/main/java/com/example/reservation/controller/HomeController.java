package com.example.reservation.controller;

import com.example.reservation.domain.FitDTO;
import com.example.reservation.domain.UserDTO;
import com.example.reservation.utils.UiUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController extends UiUtils {

    @GetMapping("/home")
    public String joinForm(@ModelAttribute UserDTO userDTO,
                           @ModelAttribute FitDTO fitDTO,
                           Model model, HttpSession session){
        System.out.println("홈 세션 : " + (String) session.getAttribute("num") +
                ", " + (String) session.getAttribute("role"));

        return "main";
    }
}