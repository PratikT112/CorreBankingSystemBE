package com.pratikt112.correbankingsystembe.model.ccnt;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CcntId implements Serializable {

    @Column(name = "SOC_NO", length = 3, nullable = false)
    String socNo;

    @Column(name = "CIF_NO", length = 16)
    String cifNo;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof CcntId)) return false;
        CcntId ccntId = (CcntId) o;
        return Objects.equals(socNo, ccntId.socNo) && Objects.equals(cifNo, ccntId.cifNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socNo, cifNo);
    }
}
