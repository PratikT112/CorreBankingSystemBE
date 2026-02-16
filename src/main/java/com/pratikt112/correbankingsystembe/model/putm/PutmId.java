package com.pratikt112.correbankingsystembe.model.putm;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PutmId implements Serializable {
    @Column(name = "PERM_CODE", nullable = false, length = 50)
    private String permCode;

    @Column(name = "USER_TYPE_CODE", length = 2, nullable = false)
    private String userTypeCode;

    @Override
    public int hashCode() {
        return Objects.hash(permCode, userTypeCode);
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof PutmId)) return false;
        PutmId that = (PutmId) obj;
        return Objects.equals(permCode, that.permCode) &&
                Objects.equals(userTypeCode, that.userTypeCode);
    }
}
