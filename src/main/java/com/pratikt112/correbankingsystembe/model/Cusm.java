package com.pratikt112.correbankingsystembe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name="CUSM")
@AllArgsConstructor
@NoArgsConstructor
public class Cusm implements Serializable {

    @Id
    @Column(name="SOC_CODE", length = 3, nullable = false)
    private String socCode;

    @Id
    @Column(name = "CUST_ACCT_NO", length = 16, nullable = false)
    private String custAcctNo;

    @Column(name = "TIER_CUST_TYPE", length = 10, nullable = false)
    private String tier_cust_type;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESI_STATUS", length = 1, nullable = false, columnDefinition = "CHAR(1) CHECK(RESI_STATUS IN ('1','2','3','4','5'))")
    private String resiStatus;

    @Column(name = "CUSTOMER_TYPE", length = 2, nullable = false, columnDefinition = "CHAR(2) CHECK(CUSTOMER_TYPE IN ('01','02'))")
    private String customerType;

    @Column(name = "CUSTOMER_STATUS", length = 3)
    private String customerStatus;

    @Column(name = "CREATE_DT", length = 8)
    private String createDt;

    @Column(name = "BSR_ORG_CODE", length = 3)
    private String bsrOrgCode;

    @Column(name = "CIS_ORG_CODE", length = 3)
    private String cisOrgCode;

    @Column(name = "CHNL_ID", length = 5)
    private String chnlId;

    @Column(name = "MAKER_ID", length = 7)
    private String makerId;

    @Column(name = "CHECKER_ID", length = 7)
    private String checkerId;
}

