package com.example.reservation.mappers;

import com.example.reservation.domain.LoginDTO;
import com.example.reservation.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface UserMapper {

    // 가입
    @Options(useGeneratedKeys = true, keyProperty = "num")
    public int insertUser(UserDTO userDTO);

    // 로그인한 아이디 db 존재여부
    public int existsUser(String id);
    
    // 로그인한 아이디 존재한다면 비밀번호 확인
    public LoginDTO userPassword(String id);
    
    // 조회
    public UserDTO selectUser(Long num);

    // 수정
    public int updateUser(UserDTO userDTO);

    // 삭제
    public int deleteUser(Long num);

    // 목록조회
    public List<UserDTO> selectUserList();

    // 데이터 수
    public int totalUserCount();
}
