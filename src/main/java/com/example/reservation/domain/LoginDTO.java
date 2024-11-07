package com.example.reservation.domain;

import com.example.reservation.constant.ROLE;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String num;
    private String id;
    private String pw;
    private String role;
}