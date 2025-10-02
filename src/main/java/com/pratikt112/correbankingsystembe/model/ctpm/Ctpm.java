package com.pratikt112.correbankingsystembe.model.ctpm;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="CTPM")
public class Ctpm implements Serializable {
    @Id
    @Column(name = "TIERED_CUST", length = 8, nullable = false)
    private String tieredCust;

    @Column(name = "DOC_REQD", length = 1)
    private String docReqd;
}
