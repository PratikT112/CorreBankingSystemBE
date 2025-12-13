package com.pratikt112.correbankingsystembe.model.turn;


import com.pratikt112.correbankingsystembe.utility.DdMmYyyyStringToLocalDateConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TURN")
public class Turn {

    @EmbeddedId
    private TurnId id;

    @NotNull
    @Digits(integer = 17, fraction = 3, message = "Annual Income / TurnOver must be of maximum 17 digits")
    @Column(name = "TURNOVER", nullable = false, precision = 17, scale = 3)
    private BigDecimal turnOver;

    @NotNull
    @Pattern(regexp = "[YN]")
    @Column(name = "CONSENT_DATA", nullable = false, length = 1)
    private String consentData = "N";

    @NotNull(message = "CONSENT_DATA_DT must not be blank")
    @Column(name = "CONSENT_DATA_DT", nullable = false, length = 8)
    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    private LocalDate consentDataDt;
}
