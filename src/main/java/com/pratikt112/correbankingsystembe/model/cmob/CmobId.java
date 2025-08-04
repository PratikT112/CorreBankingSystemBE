package com.pratikt112.correbankingsystembe.model.cmob;
import com.pratikt112.correbankingsystembe.enums.Identifier;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CmobId implements Serializable {

    @Column(name = "SOC_NO", length = 3, columnDefinition = "DEFAULT '003'")
    private String socNo;

    @Column(name = "CUST_NO", length = 16)
    private String custNo;

    @Column(name = "IDENTIFIER", length = 1, nullable = false )
    private String identifier;


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof CmobId)) return false;
        CmobId that = (CmobId) o;
        return Objects.equals(socNo, that.socNo) &&
                Objects.equals(custNo, that.custNo) &&
                Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode(){
        return Objects.hash(socNo, custNo, identifier);
    }

}