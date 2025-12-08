package com.pratikt112.correbankingsystembe.model.cuid;

import com.pratikt112.correbankingsystembe.utility.DdMmYyyyStringToLocalDateConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "CUID")
public class Cuid implements Serializable {

    @EmbeddedId
    @NotNull(message = "Composite key is required")
    private CuidId id;

    @NotBlank(message = "ID_NUMBER should not be blank")
    @Size(max = 24, message = "idNumber must be at most 24 characters")
    @Column(name = "ID_NUMBER", length = 24)
    private String idNumber;

    @NotNull(message = "ID_EXPIRY_DATE is required")
    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "ID_EXPIRY_DATE", length = 8)
    private LocalDate idExpiryDate;

    @NotBlank(message = "ID_ISSUE_AT is required")
    @Size(max = 30, message = "idIssueAt must be at most 30 characters")
    @Column(name = "ID_ISSUE_AT", length = 30)
    private String idIssueAt;

    @NotBlank(message = "ID_REMARK should not be blank")
    @Size(max = 20, message = "idRemark must be at most 20 characters")
    @Column(name = "ID_REMARK", length = 20)
    private String idRemark;

    @NotBlank(message = "ID_MAIN is required")
    @Size(max = 1, message = "idMain must be exactly 1 character")
    @Column(name = "ID_MAIN", length = 1)
    private String idMain;

    @NotNull(message = "ID_ISSUE_DATE is required")
    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "ID_ISSUE_DATE", length = 8)
    private LocalDate idIssueDate;
}