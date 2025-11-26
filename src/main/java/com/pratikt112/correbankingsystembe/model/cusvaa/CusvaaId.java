package com.pratikt112.correbankingsystembe.model.cusvaa;


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
public class CusvaaId implements Serializable {
    @Column(name = "INST_NO", length = 3, columnDefinition = "DEFAULT '003'")
    private String instNo;

    @Column(name = "CUST_NO", length = 16, nullable = false)
    private String custNo;

    @Column(name = "REC_NO", length = 4, nullable = false)
    private String recNo;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof CusvaaId)) return false;
        CusvaaId that = (CusvaaId) o;
        return Objects.equals(instNo, that.instNo) &&
                Objects.equals(custNo, that.custNo) &&
                Objects.equals(recNo, that.recNo);
    }

    @Override
    public int hashCode(){
        return Objects.hash(instNo, custNo, recNo);
    }
}

