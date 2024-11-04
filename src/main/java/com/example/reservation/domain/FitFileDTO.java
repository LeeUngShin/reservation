package com.example.reservation.domain;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FitFileDTO extends CommonDTO{
    private Long num;
    private Long fitNum;
    private Long mainFileNum;
    private String originalFileName;
    private String storedFileName;

}
