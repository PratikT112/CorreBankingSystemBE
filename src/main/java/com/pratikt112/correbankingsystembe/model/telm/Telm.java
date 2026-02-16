package com.pratikt112.correbankingsystembe.model.telm;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TELM")
public class Telm {

    @EmbeddedId
    TelmId id;

    @NotBlank(message = "BRANCH_NO is required for teller")
    @Column(name = "BRCH_NO", length = 5)
    private String brchNo;

    @Column(name = "PRIM_BRANCH", length = 5)
    private String primBranch;

    @Column(name = "TERM_IN_BRANCH", length = 3)
    private String termInBranch;

    @Column(name = "LAST_BRANCH_SIGNON", length = 5)
    private String lastBranchSignon;

    @Column(name = "SIGN_ON_DATE")
    private LocalDate signOnDate;

    @Column(name = "POST_DATE")
    private LocalDate postDate;

    @Column(name = "SIGNON_FLAG", length = 1)
    private String signonFlag;

    @Column(name = "STAT", length = 2)
    private String stat;

    @Column(name = "CAPABLE", length = 2)
    private String capable;

    @Column(name = "PRIM_CAP", length = 2)
    private String primCap;

    @NotBlank(message = "TELLER_NAME is required")
    @Column(name = "TELLER_NAME", length = 20)
    private String tellerName;

    @NotBlank(message = "TELLER_PASSWORD is required")
    @Column(name = "TELLER_PWORD", length = 100)
    private String tellerPword;

    @Column(name = "TELLER_PWORD_RETRY", length = 2)
    private String tellerPwordRetry;

    @Column(name = "CHANGE_PWORD_DATE")
    private LocalDate changePwordDate;

    @Column(name = "TELLER_AUTHORITY", length = 1)
    private String tellerAuthority;

    @NotBlank(message = "USER_TYPE is required")
    @Column(name = "USER_TYPE", length = 2)
    private String userType;

    @Column(name = "START_TIME")
    private LocalTime startTime;

    @Column(name = "END_TIME")
    private LocalTime endTime;

    @Column(name = "PRIM_USER_TYPE", length = 2)
    private String primUserType;

    @Column(name = "LST_SIGN_ON_DATE")
    private LocalDate lstSignOnDate;

    @Column(name = "LST_SIGN_ON_TIME")
    private LocalTime lstSignOnTime;

    @Column(name = "LST_UNSIGN_ON_DATE")
    private LocalDate lstUnsignOnDate;

    @Column(name = "LST_UNSIGN_ON_TIME")
    private LocalTime lstUnsignOnTime;

    @Column(name = "LST_UNSIGN_ERRCODE", length = 4)
    private String lstUnsignErrcode;

    @NotNull(message = "TELLER_DOB is required")
    @Column(name = "TELLER_DOB")
    private LocalDate tellerDob;

    @Column(name = "TELLER_IDENTIFIER", length = 1)
    private String tellerIdentifier = "P";

    @PrePersist
    public void onCreate(){
        if(this.primBranch == null){
            this.primBranch = this.brchNo;
        }

        if(this.termInBranch == null){
            this.termInBranch = "001";
        }

        if(this.postDate == null){
            this.postDate = LocalDate.now();
        }

        if(this.signonFlag == null){
            this.signonFlag = "N";
        }

        if(this.stat == null){
            this.stat = "00";
        }

        if(this.capable == null || this.capable.isBlank()){
            this.capable = "16";
        }

        if(this.primCap == null || this.primCap.isBlank()){
            this.primCap = this.capable;
        }

        if(this.startTime == null){
            this.startTime = LocalTime.MIDNIGHT;
        }

        if(this.endTime == null) {
            this.endTime = LocalTime.of(23, 59, 59);
        }

        if(this.primUserType == null || this.primUserType.isBlank()){
            this.primUserType = this.userType;
        }

        this.lstSignOnDate = null;
        this.lstSignOnTime = null;
        this.lstUnsignOnDate = null;
        this.lstUnsignOnTime = null;
        if(this.lstUnsignErrcode == null){
            this.lstUnsignErrcode = "0000";
        }
    }
}
