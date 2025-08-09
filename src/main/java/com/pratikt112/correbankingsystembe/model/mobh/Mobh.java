package com.pratikt112.correbankingsystembe.model.mobh;

import com.pratikt112.correbankingsystembe.utility.DateUtilityDDMMYYYY;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MOBH")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mobh {

    @EmbeddedId
    private MobhId id;

    @Column(name = "CUST_MOB_NO", length = 12)
    private String custMobNo;

    @Column(name = "OLD_CUST_MOB_NO", length = 12)
    private String oldCustMobNo;

    @Column(name = "ISD_CODE", length = 3)
    private String isdCode;

    @Column(name = "MAKER_ID", length = 7)
    private String makerId;

    @Column(name = "CHECKER_ID", length = 7)
    private String checkerId;

//    @Column(name = "BRANCH_NO", length = 5)
//    private String branchNo;

    @Column(name = "TRAN_CHANNEL", length = 1)
    private String tranChannel;

    @Column(name = "IDENTIFIER", length = 1)
    private String identifier;

    @Column(name = "VERIFY_FLAG", length = 1)
    private String verifyFlag;

    @PrePersist
    public void prePersist(){
        if(this.custMobNo == null) {
            this.custMobNo = "            ";
        }
        if(this.oldCustMobNo == null){
            this.oldCustMobNo = "            ";
        }
        if(this.isdCode == null){
            this.isdCode = "   ";
        }
        if(this.makerId == null){
            this.makerId = "       ";
        }
        if(this.checkerId == null){
            this.checkerId = "       ";
        }
        if(this.tranChannel == null){
            this.tranChannel = " ";
        }
    }

}

