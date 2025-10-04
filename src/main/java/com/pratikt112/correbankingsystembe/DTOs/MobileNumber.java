package com.pratikt112.correbankingsystembe.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileNumber {
    private String isd;
    private String mobNo;

    private String completeMobileNumber(){
        return isd+mobNo;
    }
}
