package com.pratikt112.correbankingsystembe.model.turn;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TurnId implements Serializable {

    @NotBlank
    @Column(name = "SOC_NO", length = 3)
    private String socNo;

    @NotBlank
    @Size(max = 16)
    @Column(name = "CUST_NO", length = 16)
    private String custNo;
}
