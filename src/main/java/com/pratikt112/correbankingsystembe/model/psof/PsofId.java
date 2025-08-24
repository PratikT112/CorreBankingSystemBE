package com.pratikt112.correbankingsystembe.model.psof;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PsofId {
    @Column(name = "SOC_NO", length = 3, nullable = false)
    private String socNo;

    @Column(name = "CUST_ACCT_NO", length = 16, nullable = false)
    private String custAcctNo;
}
