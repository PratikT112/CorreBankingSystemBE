package com.pratikt112.correbankingsystembe.model.telm;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.Name;
import java.util.HashMap;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TelmId {
    @Column(name = "SOC_NO", length = 3)
    private String socNo;

    @Column(name = "TELLER_NO", length = 7)
    private String tellerNo;


    @Override
    public int hashCode() {
        return Objects.hash(socNo, tellerNo);
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof TelmId)) return false;
        TelmId that = (TelmId) obj;
        return Objects.equals(socNo, that.socNo) &&
                Objects.equals(tellerNo, that.tellerNo);
    }
}
