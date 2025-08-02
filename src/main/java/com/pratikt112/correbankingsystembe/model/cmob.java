package com.pratikt112.correbankingsystembe.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name="CMOB")
@AllArgsConstructor
@NoArgsConstructor
public class cmob implements Serializable {
    @Id
    @Column(name="SOC_CODE", length = 3, nullable = false)
    private String socCode;

    @Id
    @Column(name = "CUST_NO", length = 16, nullable = false)
    private String custNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "IDENTIFIER", length = 1, nullable = false, columnDefinition = "CHAR(1) CHECK IDENTIFIER IN ('P','T')")
    private String identifier;

    @Column(name = "CUST_MOB_NO", length = 12)
    private String custMobNo;

    @Column(name = "ISD_CODE", length = 3)
    private String isdCode;

    @Column(name = "OLD_CUST_MOB_NO", length = 12)
    private String oldCustMobNo;

    @Column(name = "OLD_MOB_ISD_CODE", length = 3)
    private String oldMobIsdCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "VERIFY_FLAG", length = 1, columnDefinition = "CHAR(1) DEFAULT 'N' CHECK(VERIFY_FLAG IN ('Y','E','S','N','X'))")
    private String verifyFlag;

    @Column(name = "CHNL_ID", length = 5)
    private String chnlId;

    @Column(name = "MAKER_ID", length = 7)
    private String makerId;

    @Column(name = "CHECKER_ID", length = 7)
    private String checkerId;

    @Column(name = "DOV", length = 1, columnDefinition = "CHAR(1) DEFAULT ' '")
    private String dov;

}
