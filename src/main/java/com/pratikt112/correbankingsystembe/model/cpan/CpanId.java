package com.pratikt112.correbankingsystembe.model.cpan;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CpanId {
    @NotBlank
    @Column(name = "SOC_NO", length = 3)
    private String socNo;

    @NotBlank
    @Size(max = 16)
    @Column(name = "CUST_NO", length = 16)
    private String custNo;
}
