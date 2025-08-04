package com.pratikt112.correbankingsystembe.model.cmob;
import com.pratikt112.correbankingsystembe.enums.Identifier;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CmobId implements Serializable {

    @Column(name = "SOC_NO", length = 3, columnDefinition = "DEFAULT '003'")
    private String socNo;

    @Column(name = "CUST_NO", length = 16)
    private String custNo;

    @Column(name = "IDENTIFIER", length = 1, nullable = false, columnDefinition = "CHAR(1) CHECK(IDENTIFIER IN ('P','T'))")
    private Identifier identifier;
}