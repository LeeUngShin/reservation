package com.example.reservation.domain;

import com.example.reservation.paging.Criteria;
import com.example.reservation.paging.PaginationInfo;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommonDTO extends Criteria {

    private PaginationInfo paginationInfo;
    private String deleteYn;
    private LocalDateTime insertTime;
    private LocalDateTime updateTime;
    private LocalDateTime deleteTime;
}