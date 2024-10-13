package com.example.reservation.service;

import com.example.reservation.domain.LoginDTO;
import com.example.reservation.domain.UserDTO;
import com.example.reservation.mappers.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean registerUser(UserDTO userDTO) {
        int result = userMapper.insertUser(userDTO);
        if(result==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean loginUser(LoginDTO loginDTO, HttpSession session) {

        int result = userMapper.existsUser(loginDTO.getId());
        if (result == 1) {
            LoginDTO loginDTO1 = userMapper.userPassword(loginDTO.getId());
            System.out.println("db에서 가져온 데이터 : " + loginDTO1);
            if (loginDTO1.getPw().equals(loginDTO.getPw())) {
                session.setAttribute("num", loginDTO1.getNum());
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {

        int result =  userMapper.updateUser(userDTO);
        System.out.println("회원 수정 행 개수 : " + result);
        if(result == 1){
            return true;
        }
        return false;
    }

    @Override
    public UserDTO getUserDetail(Long num) {
        UserDTO userDTO = userMapper.selectUser(num);
        System.out.println("데이터베이스에서 가져온 유저정보 : "  + userDTO);
        return userDTO;
    }

    @Override
    public boolean deleteUser(Long num) {

        int result = 0;
        UserDTO userDTO =userMapper.selectUser(num);
        System.out.println("탈퇴할 유저 정보 : " + userDTO);
        System.out.println(userDTO.getDeleteYn());
        System.out.println(userDTO.getDeleteTime());
        System.out.println(userDTO.getInsertTime());
        System.out.println(userDTO.getUpdateTime());
        if(userDTO !=null && userDTO.getDeleteYn().equals("N")){
            result = userMapper.deleteUser(num);
        }

        if(result==1){
            return true;
        }
        return false;
    }

    @Override
    public List<UserDTO> getUserList() {
        return null;
    }
}
