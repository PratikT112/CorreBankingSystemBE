package com.pratikt112.correbankingsystembe.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CobData {
    // System
    private LocalDate regionDate;
    private String transactionBranch;
    private String batchTandem;
    // Personal
    private String custTierType;                    //cusm
    private String resiStatus;                      //cusm
    private PersonName custNameMain;                //cusvaa
//    private PersonName custNameMaiden;
    private LocalDate custDob;                      //cusvdd
    private String custGender;                      //cusvdd
    private String custMaritalStatus;               //cusvdd
    private PersonName custFatherName;              //cusvaa
    private PersonName custSpouseName;              //cusvaa
    private PersonName custMotherName;              //cusvaa
//    private String custIlliterate;
//    private PersonName custGuardianName;
    private String custNationality;
    private String custOccType;
    private String custOccSubType;
    private BigDecimal custAnnIncome;
    private BigDecimal custNetWorth;
    private String custReligion;
    private String custCategory;
    private String custSourceFunds;
//    private String custDisability;
//    private String custOrganizationName;
//    private String custDesignProfess;
//    private String custNatureBusiness;
//    private String custEduQualification;
//    private String custPolExpo;
//    private String custISO3166CountryJurisResi;
    private String custPOB;
    private String custISO3166CountryBirth;
    private String custCitizenship;
//    private String custTaxCountryYN;
    private String custPanF60None;
    private String custPanNo;
    private Form60 custForm60;
    private String custRelativeCode;

    // Contact
    private MobileNumber custMobNo;
    private String custTeleOff;
    private String custTeleRes;
    private String custEmail;

//    private OvdDetails custOvdDetails;
    private List<OvdDetails> custOvdDetails;
    private AddressDetails custMainAddressDetails;
    private String custConsent;
    private LocalDate custConsentDate;
//    private boolean sameAsPOA;
//    private AddressDetails custAltAddressDetails;
//    private String deemedOVDDocType;
//    private String deemedOVDDocNumber;
//    private String inbRequested;     // "yes" | "no"
//    private String inbDeliveryMode;  // "online" | "sms" | "email" | "none"
//    private String inbRefNo;
}