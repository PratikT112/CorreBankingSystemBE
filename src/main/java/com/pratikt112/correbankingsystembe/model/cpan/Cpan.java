package com.pratikt112.correbankingsystembe.model.cpan;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CPAN")
public class Cpan implements Serializable {

    @EmbeddedId
    private CpanId id;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]$", message = "Invalid PAN format")
    @Size(max = 12, message = "PAN no can be of max 12 characters")
    @Column(name = "CUST_PAN_NO", nullable = false, length = 12)
    private String custPanNo;
}
