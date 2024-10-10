package com.example.reservation.mappers;

import com.example.reservation.domain.UserDTO;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void insertUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId("user01");
        userDTO.setPw("1234");
        userDTO.setName("사용자01");
        userDTO.setEmail("user01@test.org");
        userDTO.setPost("우편번호01");
        userDTO.setAddr("기본주소01");
        userDTO.setAddrDetail("상세주소01");
        int result = userMapper.insertUser(userDTO);
        System.out.println("결과는 " + result + "입니다.");
    }

    @Test
    void selectUser() {
        UserDTO userDTO = userMapper.selectUser((long) 1);
        System.out.println("================================");
        System.out.println(userDTO);
        System.out.println("================================");
    }

    @Test
    void updateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setNum((long) 1);
        userDTO.setId("(수정)user01");
        userDTO.setPw("1234");
        userDTO.setName("(수정)사용자01");
        userDTO.setEmail("(수정)user01@test.org");
        userDTO.setPost("(수정)우편번호01");
        userDTO.setAddr("(수정)기본주소01");
        userDTO.setAddrDetail("(수정)상세주소01");
        int result = userMapper.updateUser(userDTO);
        System.out.println("결과는 " + result + "입니다.");
        if(result == 1){
            System.out.println("==============================");
            System.out.println(userMapper.selectUser((long) 1));
            System.out.println("==============================");
        }

    }

    @Test
    void deleteUser() {

        int result = userMapper.deleteUser((long) 1);
        System.out.println("결과는 " + result + "입니다.");
        if(result==1){
            System.out.println(userMapper.selectUser((long) 1));
        }
    }

    @Test
    void selectUserList() {
        System.out.println(userMapper.totalUserCount());
        List<UserDTO> userDTOList = userMapper.selectUserList();
        System.out.println("회원 리스트");
        System.out.println(userDTOList);
    }

    @Test
    void totalUserCount() {
    }
}