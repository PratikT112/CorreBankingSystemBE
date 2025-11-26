package com.pratikt112.correbankingsystembe.model.cusvaa;

import com.pratikt112.correbankingsystembe.utility.DdMmYyyyStringToLocalDateConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSVAA")
public class Cusvaa implements Serializable {
    @EmbeddedId
    private CusvaaId id;

    @Column(name = "CODE", length = 2)
    @Size(max = 2)
    private String code;

    @Column(name = "DELI", length = 1)
    @Size(max = 1)
    private String deli;

    @Column(name = "TITLE_CODE", length = 2)
    @Size(max = 2)
    private String titleCode;

    @Column(name = "NAME1", length = 40)
    @Size(max = 40)
    private String name1;                   // First Name

    @Column(name = "MID_NAME", length = 40)
    @Size(max = 40)
    private String midName;                 // Father Name

    @Column(name = "NAME2", length = 40)
    @Size(max = 40)
    private String name2;                   // Family Name

    @Column(name = "FATHER_TITLE", length = 2)
    @Size(max = 2)
    private String fatherTitle;

    @Column(name = "FATHER_NAME", length = 123)
    @Size(max = 123)
    private String fatherName;

    @Column(name = "MOTHER_TITLE", length = 2)
    @Size(max = 2)
    private String motherTitle;

    @Column(name = "MOTHER_NAME", length = 123)
    @Size(max = 123)
    private String motherName;

    @Column(name = "SPOUSE_TITLE", length = 2)
    @Size(max = 2)
    private String spouseTitle;

    @Column(name = "SPOUSE_NAME", length = 123)
    @Size(max = 123)
    private String spouseName;


    @Column(name = "RELATIVE_CODE", length = 1)
    @Size(max = 1)
    private String relativeCode;

    @Column(name = "ADD1", length = 40)
    @Size(max = 40)
    private String add1;

    @Column(name = "ADD2", length = 40)
    @Size(max = 40)
    private String add2;

    @Column(name = "ADD3", length = 40)
    @Size(max = 40)
    private String add3;

    @Column(name = "ADD4", length = 40)
    @Size(max = 40)
    private String add4;

    @Column(name = "STATE_CODE", length = 3)
    @Size(max = 3)
    private String stateCode;

    @Column(name = "CITY_CODE", length = 3)
    @Size(max = 3)
    private String cityCode;

    @Column(name = "COUNTRY_CODE", length = 2)
    @Size(max = 2)
    private String countryCode;

    @Column(name = "POSTCODE", length = 8)
    @Size(max = 8)
    private String postcode;

    @Column(name = "PHONE_NO_RES", length = 12)
    @Size(max = 12)
    private String phoneNoRes;

    @Column(name = "PHONE_NO_BUS", length = 12)
    @Size(max = 12)
    private String phoneNoBus;

    @Column(name = "FAX_NO", length = 12)
    @Size(max = 12)
    private String faxNo;

    @Column(name = "TELEX_NO", length = 12)
    @Size(max = 12)
    private String telexNo;

    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "EFFE_DATE", length = 8)
    private LocalDate effeDate;

    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "EXPI_DATE", length = 8)
    private LocalDate expiDate;

}
