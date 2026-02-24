package com.pratikt112.correbankingsystembe.model.ccnt;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CCNT")
public class Ccnt {

    @EmbeddedId
    private CcntId id;

    @Column(name = "MOB_AMEND_STATUS", length = 2, nullable = false)
    private String mobAmendStatus;


    @Column(name = "MOB_AMEND_COUNT", nullable = false)
    private int mobAmendCount;

    @Column(name = "LST_MOB_AMEND_DATE", nullable = false)
    private LocalDate LstMobAmendDate;
}
