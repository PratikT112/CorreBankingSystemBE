package com.pratikt112.correbankingsystembe.model.cky1;


import com.pratikt112.correbankingsystembe.utility.DdMmYyyyStringToLocalDateConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CKY1")
public class Cky1 {
    @EmbeddedId
    private Cky1Id id;

    @Column(name = "KYC_NO", length = 14)
    private String kycNo;

    @Column(name = "PREFIX1", length = 2)
    private String prefix1;

    @Column(name = "MAIDEN_FIRST_NAME", length = 40)
    private String maidenFirstName;

    @Column(name = "MAIDEN_MID_NAME", length = 40)
    private String maidenMidName;

    @Column(name = "MAIDEN_LAST_NAME", length = 40)
    private String maidenLastName;

    @Column(name = "PREFIX2", length = 2)
    private String prefix2;

    @Column(name = "FATHER_FIRST_NAME", length = 40)
    private String fatherFirstName;

    @Column(name = "FATHER_MID_NAME", length = 40)
    private String fatherMidName;

    @Column(name = "FATHER_LAST_NAME", length = 40)
    private String fatherLastName;

    @Column(name = "PREFIX3", length = 2)
    private String prefix3;

    @Column(name = "MOTHER_FIRST_NAME", length = 40)
    private String motherFirstName;

    @Column(name = "MOTHER_MID_NAME", length = 40)
    private String motherMidName;

    @Column(name = "MOTHER_LAST_NAME", length = 40)
    private String motherLastName;

    @Column(name = "CITZNSHP", length = 2)
    private String citizenship;

    @Column(name = "OCCP_TYPE", length = 2)
    private String occupationType;

    @Column(name = "ID_PROOF", length = 1)
    private String idProof;

    @Column(name = "ID_NO", length = 20)
    private String idNo;

    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "PASS_EXPIRY", length = 8)
    private LocalDate passExpiry;

    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "DRIV_EXPIRY", length = 8)
    private LocalDate drivExpiry;

    @Column(name = "ID_OTHER", length = 20)
    private String idOther;

    @Column(name = "DOC_TYPE", length = 2)
    private String docType;

    @Column(name = "ADD_TYPE", length = 2)
    private String addType;

    @Column(name = "ADD_PROOF", length = 1)
    private String addProof;

    @Column(name = "PROOF_OTHER", length = 20)
    private String proofOther;

    @Column(name = "CRES", length = 2)
    private String cres;

    @Column(name = "TIN", length = 20)
    private String tin;

    @Column(name = "BIRTH_PLACE", length = 20)
    private String birthPlace;

    @Column(name = "BIRTH_COUNTRY", length = 2)
    private String birthCountry;

    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "CREATE_DATE", length = 8)
    private LocalDate createDate;

    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "LAST_UPDATE_DATE", length = 8)
    private LocalDate lastUpdateDate;

    @Column(name = "STEP", length = 1)
    private String step;

    @Column(name = "LAST_UPDATE_STEP", length = 1)
    private String lastUpdateStep;

    @Column(name = "MAKER_ID", length = 7)
    private String makerId;

    @Column(name = "CHECKER_ID", length = 7)
    private String checkerId;

    @Column(name = "SCRT_FLAG", length = 1)
    private String scrtFlag;

    @Column(name = "PREFIX3", length = 2)
    private String prefix3;

    @Column(name = "SPOUSE_FIRST_NAME", length = 40)
    private String spouseFirstName;

    @Column(name = "SPOUSE_MID_NAME", length = 40)
    private String spouseMidName;

    @Column(name = "SPOUSE_LAST_NAME", length = 40)
    private String spouseLastName;
}
