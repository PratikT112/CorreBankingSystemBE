package com.pratikt112.correbankingsystembe.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CobData {
    // System
    private LocalDate regionDate;
    private String transactionBranch;
    private String batchTandem;
    // Personal
    private String custTierType;
    private String resiStatus;
    private PersonName custNameMain;
    private PersonName custNameMaiden;
    private LocalDate custDob;
    private String custGender;
    private String custMaritalStatus;
    private PersonName custFatherName;
    private PersonName custSpouseName;
    private PersonName custMotherName;
    private Integer custNoOfDependents;
    private String custIlliterate;
    private PersonName custGuardianName;
    private String custNationality;
    private String custOccType;
    private String custOccSubType;
    private BigDecimal custAnnIncome;
    private BigDecimal custNetWorth;
    private String custReligion;
    private String custCategory;
    private String custSourceFunds;
    private String custDisability;
    private String custOrganizationName;
    private String custDesignProfess;
    private String custNatureBusiness;
    private String custEduQualification;
    private String custPolExpo;
    private String custISO3166CountryJurisResi;
    private String custPOB;
    private String custISO3166CountryBirth;
    private String custCitizenship;
    private String custTaxCountryYN;
    private String custPanF60None;
    private String custPanNo;
    private Form60 custForm60;
    private String custRelativeCode;

    // Contact
    private MobileNumber custMobNo;
    private String custTeleOff;
    private String custTeleRes;
    private String custEmail;

    private OvdDetails custOvdDetails;
    private AddressDetails custMainAddressDetails;
    private boolean sameAsPOA;
    private AddressDetails custAltAddressDetails;
    private String deemedOVDDocType;
    private String deemedOVDDocNumber;
    private String inbRequested;     // "yes" | "no"
    private String inbDeliveryMode;  // "online" | "sms" | "email" | "none"
    private String inbRefNo;
}