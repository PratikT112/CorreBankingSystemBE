package com.pratikt112.correbankingsystembe.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Form60 {
    private LocalDate subDate;
    private LocalDate tranDate;
    private BigDecimal agriIncome;
    private BigDecimal otherIncome;
    private String panAppliedFlag; // "pan-appl-yes" | "pan-appl-no"
    private LocalDate panAppliedDate;
    private String panAckNo;
}
