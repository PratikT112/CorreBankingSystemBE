package com.pratikt112.correbankingsystembe.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDetails {
    private String addrType;
    private String addrProof;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String country;
    private String state;
    private String district;
    private String subDistrict;
    private String city;
    private String village;
    private String pincode;
}
