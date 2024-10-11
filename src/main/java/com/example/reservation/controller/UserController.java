package com.example.reservation.controller;

import com.example.reservation.constant.Method;
import com.example.reservation.domain.ChoiceDTO;
import com.example.reservation.domain.UserDTO;
import com.example.reservation.service.UserService;
import com.example.reservation.utils.UiUtils;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user")
public class UserController extends UiUtils {

    @Autowired
    private UserService userService;

    @GetMapping("/loginForm")
    public String loginForm(){

        return "user/login";
    }

    @GetMapping("/joinForm")
    public String joinForm(){

        return "user/join";
    }

    @PostMapping("/join")
    public String join(UserDTO userDTO, Model model){
        try {
            boolean joinResult = userService.registerUser(userDTO);
            if (joinResult) {
                return showMessageWithRedirect("회원가입에 성공했습니다.", "/user/login", Method.GET, null, model);
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

    @GetMapping("/updateForm")
    public String update(@RequestParam(value="num", required = false) Long num, Model model){

        UserDTO userDTO = userService.getUserDetail(num);

        model.addAttribute("userDTO", userDTO);
        System.out.println("수정할 데이터 : " + userDTO);
        return "/user/update";
    }

    @PostMapping("/update")
    public String updateUser(UserDTO userDTO, Model model){

        try{
            boolean updateResult = userService.updateUser(userDTO);
            if(updateResult==true){
                return showMessageWithRedirect("회원정보 수정에 성공했습니다. 다시 로그인 해주세요", "/user/login", Method.GET, null, model);

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

    @GetMapping("/deleteForm")
    public String deleteForm(){
        return "user/delete";
    }

//    @PostMapping("delete")
//    public String delete(@RequestParam(value="password")String password,
//                         @RequestParam(value="num") Long num,
//                         HttpSession session){
//        String id
//
//    }
}
