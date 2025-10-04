package com.pratikt112.correbankingsystembe.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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

    public boolean isApplicable() {
        return subDate != null
                || tranDate != null
                || agriIncome != null
                || otherIncome != null
                || panAppliedFlag != null
                || panAppliedDate != null
                || panAckNo != null;
    }
}
