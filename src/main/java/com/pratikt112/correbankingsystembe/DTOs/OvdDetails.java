package com.pratikt112.correbankingsystembe.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OvdDetails {
    private String ovdDocType;
    private String ovdDocNumber;
    private LocalDate ovdDocIssueDate;
    private LocalDate ovdDocExpiryDate;
    private String ovdDocIssuedAt;
}
