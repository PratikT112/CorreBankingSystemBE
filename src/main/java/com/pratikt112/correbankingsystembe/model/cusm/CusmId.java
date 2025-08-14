package com.pratikt112.correbankingsystembe.model.cusm;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CusmId implements Serializable {

    @Column(name = "SOC_NO", length = 3, nullable = false)
    private String socNo;

    @Column(name = "CUST_ACCT_NO", length = 16, nullable = false)
    private String custAcctNo;
}
