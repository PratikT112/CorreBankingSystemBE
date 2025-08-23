package com.pratikt112.correbankingsystembe.model.mbex;


import com.pratikt112.correbankingsystembe.utility.DdMmYyyyStringToLocalDateConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.Name;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MBEX")
public class Mbex implements Serializable {

    @EmbeddedId
    private MbexId id;

    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "MOB_EXP_DT", length = 8, nullable = false)
    private LocalDate mobExpDt;
}
