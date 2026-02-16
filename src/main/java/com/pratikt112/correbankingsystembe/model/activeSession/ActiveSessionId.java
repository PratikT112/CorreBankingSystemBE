package com.pratikt112.correbankingsystembe.model.activeSession;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActiveSessionId implements Serializable {

    @Column(name = "JTI", length = 50, nullable = false)
    private String jti;

    @Override
    public int hashCode() {
        return Objects.hash(jti);
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof ActiveSessionId)) return false;
        ActiveSessionId that = (ActiveSessionId) obj;
        return Objects.equals(jti, that.jti);
    }
}
