package com.example.reservation.service;

import com.example.reservation.domain.LoginDTO;
import com.example.reservation.domain.UserDTO;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface UserService {

    public boolean registerUser(UserDTO userDTO);

    public boolean loginUser(LoginDTO loginDTO, HttpSession session);

    public boolean updateUser(UserDTO userDTO);

    public UserDTO getUserDetail(Long num);

    public boolean deleteUser(Long num);

    public List<UserDTO> getUserList();
}
