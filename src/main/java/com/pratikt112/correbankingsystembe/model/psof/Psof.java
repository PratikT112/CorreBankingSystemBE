package com.pratikt112.correbankingsystembe.model.psof;


import com.pratikt112.correbankingsystembe.utility.DdMmYyyyStringToLocalDateConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PSOF")
public class Psof implements Serializable {

    @EmbeddedId
    private PsofId psofId;


    @Column(name = "SOURCE_FUNDS", length = 2, nullable = false)
    private String sourceFunds;

    @Column(name = "CREATE_DATE", length = 8, nullable = false)
    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    private LocalDate createDate;

    @Column(name = "MAKER_ID", length = 7)
    private String makerId;

    @Column(name = "CHECKER_ID", length = 7)
    private String checkerId;

    @Column(name = "BRANCH_NO", length = 5, nullable = false)
    private String branchNo;

}
