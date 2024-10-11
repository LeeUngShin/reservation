package com.example.reservation.service;

import com.example.reservation.domain.UserDTO;
import com.example.reservation.mappers.UserMapper;
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
    public boolean updateUser(UserDTO userDTO) {

        int result =  userMapper.updateUser(userDTO);
        if(result == 1){
            return true;
        }
        return false;
    }

    @Override
    public UserDTO getUserDetail(Long num) {
        UserDTO userDTO = userMapper.selectUser(num);
        return userDTO;
    }

    @Override
    public boolean deleteUser(String id, String password) {
        return false;
    }

    @Override
    public List<UserDTO> getUserList() {
        return null;
    }
}
