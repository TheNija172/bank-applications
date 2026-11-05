package com.bank.applications.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponse {

    private Long id;

    private BigDecimal amount;

    private Integer score;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private ClientResponse client;
}
