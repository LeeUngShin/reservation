package com.example.reservation.domain;

import com.example.reservation.constant.ROLE;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends CommonDTO{

    private Long num;
    private String id;
    private String pw;
    private String confirm_password;
    private String name;
    private String email;
    private String post;
    private String addr;
    private String addrDetail;
    private String role;
}