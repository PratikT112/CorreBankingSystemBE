package com.pratikt112.correbankingsystembe.model.mbex;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.Name;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MBEX")
public class Mbex implements Serializable {

    @EmbeddedId
    private MbexId id;

    @Column(name = "MOB_EXP_DT", length = 8, nullable = false)
    private String mobExpDt;
}
