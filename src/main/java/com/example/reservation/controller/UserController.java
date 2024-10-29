package com.example.reservation.controller;

import com.example.reservation.constant.Method;
import com.example.reservation.domain.LoginDTO;
import com.example.reservation.domain.UserDTO;
import com.example.reservation.service.UserService;
import com.example.reservation.utils.UiUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController extends UiUtils {

    @Autowired
    private UserService userService;

    @GetMapping("/joinForm")
    public String joinForm(){

        return "user/join";
    }

    @PostMapping("/join")
    public String join(UserDTO userDTO, Model model){
        System.out.println("회원가입때 들어온 정보 : " + userDTO);
        try {
            boolean joinResult = userService.registerUser(userDTO);
            if (joinResult) {
                return showMessageWithRedirect("회원가입에 성공했습니다.", "/user/loginForm", Method.GET, null, model);
            }
        }catch(DataAccessException e) {
            e.printStackTrace();
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/user/join", Method.GET, null, model);
        }catch(Exception e) {
            e.printStackTrace();
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/user/join", Method.GET, null, model);
        }
        return showMessageWithRedirect("회원가입에 실패했습니다.", "/user/join", Method.GET, null, model);
    }

    @GetMapping("/loginForm")
    public String loginForm(){

        return "user/login";
    }

    @PostMapping("/login")
    public String login(LoginDTO loginDTO, Model model,
                        HttpSession session){

        System.out.println("로그인 때 들어온 정보 : " + loginDTO);
        boolean loginResult = userService.loginUser(loginDTO, session);
        if(loginResult==true){
            System.out.println("로그인 성공 세션값 : " + session.getAttribute("num"));
            return showMessageWithRedirect("로그인 성공", "/home", Method.GET, null, model);
        }
        else{
            System.out.println("로그인 실패 세션값 : " + session.getAttribute("num"));
            return showMessageWithRedirect("로그인 실패, 아이디와 비밀번호를 확인해주세요", "/user/loginForm", Method.GET, null, model);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        String id = (String) session.getAttribute("num");
        if (id == null || id.isEmpty()) {
            return showMessageWithRedirect("세션에 데이터가 없습니다.", "/user/loginForm", Method.GET, null, model);
        } else {
            session.invalidate();
            return showMessageWithRedirect("로그아웃 성공", "/home", Method.GET, null, model);

        }

    }

    @GetMapping("/updateForm")
    public String update(@RequestParam(value="num", required = false) Long num, Model model){

        UserDTO userDTO = userService.getUserDetail(num);
        if(userDTO == null){
            return showMessageWithRedirect("회원정보가 없습니다.", "/home", Method.GET, null, model);
        }
        System.out.println("수정할 유저 정보 : " + userDTO);
        model.addAttribute("userDTO", userDTO);
        return "/user/update";
    }

    @PostMapping("/update")
    public String updateUser(HttpSession session, UserDTO userDTO, Model model){

        try{
            boolean updateResult = userService.updateUser(userDTO);
            System.out.println("회원수정시 들어온 데이터 : " + userDTO);
            System.out.println("회원성공 여부 : " + false);
            if(updateResult==true){
                session.invalidate();
                return showMessageWithRedirect("회원정보 수정에 성공했습니다. 다시 로그인 해주세요", "/user/loginForm", Method.GET, null, model);

            }
        }catch(DataAccessException e) {
            e.printStackTrace();
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/home", Method.GET, null, model);
        }catch(Exception e) {
            e.printStackTrace();
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/home", Method.GET, null, model);
        }
        return showMessageWithRedirect("회원정보 수정에 실패했습니다.", "/home", Method.GET, null, model);
    }

    @GetMapping("/delete")
    public String deleteForm(@RequestParam(value="num", required=false) Long num, Model model,
                             HttpSession session){

        if(num==null) {
            return showMessageWithRedirect("올바르지 않은 접근입니다.", "/home", Method.GET, null, model);
        }

        try{
            boolean deleteResult = userService.deleteUser(num);
            if(deleteResult==false){
                return showMessageWithRedirect("탈퇴에 실패했습니다..", "/home", Method.GET, null, model);
            }
        }catch(DataAccessException e) {
            e.printStackTrace();
            return showMessageWithRedirect("데이터베이스 처리 중 오류 발생하였습니다.", "/home", Method.GET, null, model);
        }catch(Exception e) {
            e.printStackTrace();
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/home", Method.GET, null, model);
        }
        session.invalidate();
        return showMessageWithRedirect("탈퇴에 성공했습니다..", "/user/loginForm", Method.GET, null, model);
    }
}