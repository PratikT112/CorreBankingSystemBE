package com.pratikt112.correbankingsystembe.model.mobh;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MobhId implements Serializable {

    @Column(name = "SOC_NO", length = 3, nullable = false)
    private String socNo;

    @Column(name = "CUST_NO", length = 16, nullable = false)
    private String custNo;

    @Column(name = "CHANGE_DT", length = 8, nullable = false)
    private String changeDt;

    @Column(name = "CHANGE_TIME", length = 9, nullable = false)
    private String changeTime;
}

