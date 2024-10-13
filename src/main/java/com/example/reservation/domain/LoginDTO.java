package com.example.reservation.domain;

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
}