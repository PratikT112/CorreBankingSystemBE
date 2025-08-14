package com.pratikt112.correbankingsystembe.model.cusm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSM")
public class Cusm implements Serializable {

    @EmbeddedId
    private CusmId id;

    @Column(name = "PRIM_ACCT", length = 17)
    private String primAcct;

    @Column(name = "THIS_ACCT_STATUS", length = 1)
    private String thisAcctStatus;

    @Column(name = "MAIL_IND", length = 1)
    private String mailInd;

    @Column(name = "NOTICE_IND", length = 1)
    private String noticeInd;

    @Column(name = "CUSTOMER_TYPE", length = 2)
    private String customerType;

    @Column(name = "MAIL_IND_EXP_DT", length = 8)
    private String mailIndExpDt;

    @Column(name = "DOMESTIC_RISK", length = 2)
    private String domesticRisk;

    @Column(name = "CROSS_BORDER_RISK", length = 2)
    private String crossBorderRisk;

    @Column(name = "LAST_USED_ACCT_NO", length = 17)
    private String lastUsedAcctNo;

    @Column(name = "CUSTOMER_STATUS", length = 3)
    private String customerStatus;

    @Column(name = "SEGMENT_CODE", length = 4)
    private String segmentCode;

    @Column(name = "CREATE_DT", length = 8)
    private String createDt;

    @Column(name = "HOME_BRANCH_NO", length = 5)
    private String homeBranchNo;

    @Column(name = "COUNTRY_OF_RISK", length = 2)
    private String countryOfRisk;

    @Column(name = "TIER_CUST_TYPE", length = 8)
    private String tierCustType;

    @Column(name = "MISLA_ORG_CODE", length = 3)
    private String mislaOrgCode;

    @Column(name = "BSR_ORG_CODE", length = 3)
    private String bsrOrgCode;

    @Column(name = "RESI_STATUS", length = 1)
    private String resiStatus;

    @Column(name = "LAST_STAT_CHG_DT", length = 8)
    private String lastStatChgDt;

    @Column(name = "DELIVERY_MODE", length = 2)
    private String deliveryMode;

    @Column(name = "CUST_TAX_PAN", length = 20)
    private String custTaxPan;

    @Column(name = "CUST_VOTER_ID", length = 20)
    private String custVoterId;

    @Column(name = "CUST_EVAL_FLAG", length = 1)
    private String custEvalFlag;

    @Column(name = "POST_CHECKER_ID", length = 7)
    private String postCheckerId;
}

