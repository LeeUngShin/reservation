package com.example.reservation.mappers;

import com.example.reservation.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    // 가입
    public int insertUser(UserDTO userDTO);

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
