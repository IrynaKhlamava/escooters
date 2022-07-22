package com.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private Integer number;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    private String rate;

    private Long userId;

    private Long scooterId;

    private Double sum;

}
