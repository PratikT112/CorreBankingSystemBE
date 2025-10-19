package com.pratikt112.correbankingsystembe.model.cr60;

import com.pratikt112.correbankingsystembe.utility.DdMmYyyyStringToLocalDateConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CR60")
public class Cr60 {

    @Id
    @Column(name = "KEY_1", length = 19, nullable = false)
    private String key1;

    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "CR60_SBMT_DT")
    private LocalDate cr60SubmitDate;

    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "TXN_DT")
    private LocalDate txnDate;

    @Column(name = "AGRI_INCOME", precision = 17, scale = 3)
    private BigDecimal agriIncome;

    @Column(name = "OTHER_INCOME", precision = 17, scale = 3)
    private BigDecimal otherIncome;

    @Column(name = "UID_NO", length = 12)
    private String uidNo;

    @Column(name = "PAN_APPLY", length = 3)
    private String panApply;

    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "PAN_APL_DT")
    private LocalDate panApplyDate;

    @Column(name = "PAN_ACK_NO", length = 16)
    private String panAckNo;

    @Column(name = "TXN_BRANCH", length = 5)
    private String txnBranch;

    @Column(name = "CR60_ACK_NO", length = 22)
    private String cr60AckNo;

    @Column(name = "CUST_NAME", length = 120)
    private String custName;

    @Column(name = "FATHER_NAME", length = 40)
    private String fatherName;

    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "CUST_DOB")
    private LocalDate custDob;

    @Column(name = "CUST_MOB_NO", length = 12)
    private String custMobileNo;

    @Column(name = "CUST_TELE_NO", length = 12)
    private String custTelephoneNo;

    @Column(name = "CUST_ID_TYPE", length = 4)
    private String custIdType;

    @Column(name = "CUST_ID_NUM", length = 24)
    private String custIdNum;

    @Column(name = "ADDR1", length = 40)
    private String addr1;

    @Column(name = "ADDR2", length = 40)
    private String addr2;

    @Column(name = "ADDR3", length = 40)
    private String addr3;

    @Column(name = "ADDR4", length = 40)
    private String addr4;

    @Column(name = "CITY_CODE", length = 3)
    private String cityCode;

    @Column(name = "STATE_CODE", length = 3)
    private String stateCode;

    @Column(name = "COUNTRY_CODE", length = 3)
    private String countryCode;

    @Column(name = "POSTCODE", length = 8)
    private String postcode;

    @Column(name = "MAKER_ID", length = 7)
    private String makerId;

    @Column(name = "CHECKER_ID", length = 7)
    private String checkerId;
}
