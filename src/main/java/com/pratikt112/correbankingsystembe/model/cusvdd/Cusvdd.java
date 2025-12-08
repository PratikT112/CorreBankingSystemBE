package com.pratikt112.correbankingsystembe.model.cusvdd;

import com.pratikt112.correbankingsystembe.enums.Gender;
import com.pratikt112.correbankingsystembe.utility.DdMmYyyyStringToLocalDateConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSVDD")
public class Cusvdd {
    @Id
    @Column(name = "KEY_1", length = 19, nullable = false)
    private String key1;

    @Column(name = "ID1", length = 20)
    private String id1;

    @Column(name = "BIRTH_DATE_1", length = 8)
    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    private LocalDate birthDate1;

    @Column(name = "DEATH_DATE", length = 8)
    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    private LocalDate deathDate;

    @Column(name = "SEX_CODE", length = 1)
    private String sexCode;

    @Column(name = "MARITAL_STATUS", length = 1)
    private String maritalStatus;

    @Column(name = "OCCUP_DESCRIP", length = 30)
    private String occupDesc;

    @Column(name = "OCCUPATION_CODE", length = 5)
    private String occupationCode;

    @Column(name = "RES_COUNTRY_CODE", length = 2)
    private String resCountryCode;

    @Column(name = "BIRTH_PLACE", length = 30)
    private String birthPlace;

    @Column(name = "POSTCODE", length = 8)
    private String postcode;

    @Column(name = "THRE_LIM", precision = 14, scale = 0)
    @Digits(integer = 14, fraction = 2, message = "Threshold Limit must be of maximum 14 digits")
    private BigDecimal threLim;
}
