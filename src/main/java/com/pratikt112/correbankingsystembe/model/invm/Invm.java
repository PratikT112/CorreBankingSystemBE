package com.pratikt112.correbankingsystembe.model.invm;

import com.pratikt112.correbankingsystembe.utility.DdMmYyyyStringToLocalDateConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "INVM",
        uniqueConstraints = {
                @UniqueConstraint(name = "INVM_UK_01",
                        columnNames = {"BRANCH_NO", "ACCT_TYPE", "INT_CAT", "KEY_1"})
        }
)
@Getter
public class Invm {

    @Id
    @Column(name = "KEY_1", length = 19)
    private String key1;

    private static String pad(String value, int len) {
        if (value == null) value = "";
        if (value.length() > len) return value.substring(0, len);
        return String.format("%1$-" + len + "s", value).replace(' ', '\u0000');
    }

    // Pad on every setter
    public void setKey1(String key1) { this.key1 = pad(key1, 19); }

    // ----------- NUMERIC FIELDS -----------
    @Column(name="NO_OF_TRNS")
    private Long noOfTrns = 0L;

    @Column(name="NO_OF_PERD_TRNS")
    private Long noOfPerdTrns = 0L;

    @Column(name="NO_OF_RCD_TRNS")
    private Long noOfRcdTrns = 0L;


    @Column(name="NO_OF_NON_FIN_TRNS")
    private Long noOfNonFinTrns = 0L;

    // ----------- CHAR FIELDS WITH PADDING -----------

    @Column(name="SYS", length = 3)
    private String sys;
    public void setSys(String v) { this.sys = pad(v, 3); }

    @Column(name="BRANCH_NO", length = 5)
    private String branchNo;
    public void setBranchNo(String v) { this.branchNo = pad(v, 5); }

    @Column(name="CURR_STATUS", length = 2)
    private String currStatus;
    public void setCurrStatus(String v) { this.currStatus = pad(v, 2); }

    @Column(name="ACCT_TYPE", length = 4)
    private String acctType;
    public void setAcctType(String v) { this.acctType = pad(v, 4); }

    @Column(name="INT_CAT", length = 4)
    private String intCat;
    public void setIntCat(String v) { this.intCat = pad(v, 4); }

    @Column(name="CURRENCY", length = 3)
    private String currency;
    public void setCurrency(String v) { this.currency = pad(v, 3); }

    @Column(name="CURR_COND", length = 1)
    private String currCond;
    public void setCurrCond(String v) { this.currCond = pad(v, 1); }


    @Column(name="CUSTOMER_NO", length = 16)
    private String customerNo;
    public void setCustomerNo(String v) { this.customerNo = pad(v, 16); }

    @Column(name="ATM_LIMIT_FLAG", length = 1)
    private String atmLimitFlag;
    public void setAtmLimitFlag(String v) { this.atmLimitFlag = pad(v, 1); }

    // Numeric fields
    @Column(name="CR_LIMIT", precision = 17, scale = 3)
    private BigDecimal crLimit = BigDecimal.ZERO;

    @Column(name="CURR_BAL", precision = 17, scale = 3)
    private BigDecimal currBal = BigDecimal.ZERO;

    @Column(name="UNCL_CHQ_VAL", precision = 17, scale = 3)
    private BigDecimal unclChqVal = BigDecimal.ZERO;

    @Column(name="HOLD_VAL", precision = 17, scale = 3)
    private BigDecimal holdVal = BigDecimal.ZERO;

    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name="ACCT_OPEN_DT", length = 8)
    private LocalDate acctOpenDt;

    @Column(name="NO_OF_HOLDS")
    private Long noOfHolds = 0L;

    @Column(name="NO_OF_STOPS")
    private Long noOfStops = 0L;

    @Column(name="ATM_ACCT_FLAG", length = 1)
    private String atmAcctFlag;
    public void setAtmAcctFlag(String v) { this.atmAcctFlag = pad(v, 1); }

    @Column(name="CHQ_BOOK_FLAG", length = 1)
    private String chqBookFlag;
    public void setChqBookFlag(String v) { this.chqBookFlag = pad(v, 1); }

    @Column(name="MINOR_AC", length = 1)
    private String minorAc;
    public void setMinorAc(String v) { this.minorAc = pad(v, 1); }

    @Column(name="ACCT_IND", length = 1)
    private String acctInd;
    public void setAcctInd(String v) { this.acctInd = pad(v, 1); }

    @Column(name="CHR_CNT01")
    private Long chrCnt01 = 0L;

    @Column(name="CHR_VAL01", precision = 17, scale = 3)
    private BigDecimal chrVal01 = BigDecimal.ZERO;

    @Column(name="SMS_REQD", length = 1)
    private String smsReqd;
    public void setSmsReqd(String v) { this.smsReqd = pad(v, 1); }

    @Column(name="PBOOK_NO", length = 10)
    private String pbookNo;
    public void setPbookNo(String v) { this.pbookNo = pad(v, 10); }

    @Column(name="CUST_EVAL_FLAG", length = 1)
    private String custEvalFlag;
    public void setCustEvalFlag(String v) { this.custEvalFlag = pad(v, 1); }
}
