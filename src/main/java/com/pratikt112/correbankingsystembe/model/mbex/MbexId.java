package com.pratikt112.correbankingsystembe.model.mbex;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MbexId implements Serializable {

    @Column(name = "SOC_NO", length = 3, nullable = false)
    private String socNo;

    @Column(name = "CUST_NO", length = 16, nullable = false)
    private String custNo;
}
