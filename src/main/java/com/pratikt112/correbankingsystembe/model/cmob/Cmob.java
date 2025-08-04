package com.pratikt112.correbankingsystembe.model.cmob;

import com.pratikt112.correbankingsystembe.enums.VerifyFlag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(
        name = "CMOB",
        uniqueConstraints = {
                @UniqueConstraint(name = "CMOB_AK01", columnNames = {"SOC_NO", "CUST_MOB_NO", "CUST_NO", "IDENTIFIER"}),
                @UniqueConstraint(name = "CMOB_AK02", columnNames = {"SOC_NO", "CHANGE_DATE", "CUST_NO", "IDENTIFIER"}),
                @UniqueConstraint(name = "CMOB_AK03", columnNames = {"SOC_NO", "OLD_CUST_MOB_NO", "CUST_NO", "IDENTIFIER"})
        }
)
@AllArgsConstructor
@NoArgsConstructor
public class Cmob implements Serializable {

    @EmbeddedId
    private CmobId id;

    @Column(name = "CUST_MOB_NO", length = 12)
    private String custMobNo;

    @Column(name = "ISD_CODE", length = 3)
    private String isdCode;

    @Column(name = "OLD_CUST_MOB_NO", length = 12)
    private String oldCustMobNo;

    @Column(name = "OLD_MOB_ISD_CODE", length = 3)
    private String oldMobIsdCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "VERIFY_FLAG", length = 1)
    private VerifyFlag verifyFlag = VerifyFlag.N;


    @Column(name = "CHNL_ID", length = 5)
    private String chnlId;

    @Column(name = "CHANGE_DATE", length = 9)
    private String changeDate;

    @Column(name = "MAKER_ID", length = 7)
    private String makerId;

    @Column(name = "CHECKER_ID", length = 7)
    private String checkerId;

    @Column(name = "DOV", length = 1)
    private String dov = "0";

}
