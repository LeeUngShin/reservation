package com.example.reservation.domain;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceDTO {

    private String bigRegion;
    private String smallRegion;
    private String isIn;
}
