package com.example.reservation.service;

import com.example.reservation.domain.UserDTO;

import java.util.List;

public interface UserService {

    public boolean registerUser(UserDTO userDTO);

    public boolean updateUser(UserDTO userDTO);

    public UserDTO getUserDetail(Long num);

    public boolean deleteUser(String id, String password);

    public List<UserDTO> getUserList();
}
