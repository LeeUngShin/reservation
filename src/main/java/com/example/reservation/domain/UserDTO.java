package com.example.reservation.domain;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long num;
    private String id;
    private String pw;
    private String name;
    private String email;
    private String post;
    private String addr;
    private String addrDetail;
}