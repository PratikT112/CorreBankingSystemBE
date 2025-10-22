package com.pratikt112.correbankingsystembe.model.cuid;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CuidId implements Serializable {
    @Column(name = "INST_NO", length = 3, columnDefinition = "DEFAULT '003'")
    private String instNo;

    @Column(name = "CUST_NO", length = 16, nullable = false)
    private String custNo;

    @Column(name = "ID_TYPE", length = 4, nullable = false)
    private String idType;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof CuidId)) return false;
        CuidId that = (CuidId) o;
        return Objects.equals(instNo, that.instNo) &&
                Objects.equals(custNo, that.custNo) &&
                Objects.equals(idType, that.idType);
    }

    @Override
    public int hashCode(){
        return Objects.hash(instNo, custNo, idType);
    }
}
