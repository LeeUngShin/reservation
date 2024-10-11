package com.example.reservation.controller;

import com.example.reservation.domain.UserDTO;
import com.example.reservation.utils.UiUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController extends UiUtils {

    @GetMapping("/home")
    public String joinForm(@ModelAttribute UserDTO userDTO, Model model){

        return "main";
    }
}