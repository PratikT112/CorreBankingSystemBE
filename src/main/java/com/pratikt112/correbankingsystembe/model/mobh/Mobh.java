package com.pratikt112.correbankingsystembe.model.mobh;

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
    private String custMobNo = "            ";  // 12 spaces

    @Column(name = "OLD_CUST_MOB_NO", length = 12)
    private String oldCustMobNo = "            ";  // 12 spaces

    @Column(name = "ISD_CODE", length = 3)
    private String isdCode = "   ";  // 3 spaces, but default was '91' in DDL

    @Column(name = "MAKER_ID", length = 7)
    private String makerId = "       ";  // 7 spaces

    @Column(name = "CHECKER_ID", length = 7)
    private String checkerId = "       ";  // 7 spaces

//    @Column(name = "BRANCH_NO", length = 5)
//    private String branchNo = "     ";  // 5 spaces

    @Column(name = "TRAN_CHANNEL", length = 1)
    private String tranChannel = " ";  // 1 space

    @Column(name = "IDENTIFIER", length = 1)
    private String identifier = " ";  // 1 space

    @Column(name = "VERIFY_FLAG", length = 1)
    private String verifyFlag = " ";  // 1 space
}

