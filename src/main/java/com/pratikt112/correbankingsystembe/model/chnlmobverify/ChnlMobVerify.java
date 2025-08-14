package com.pratikt112.correbankingsystembe.model.chnlmobverify.;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CHNLMOBVERIFY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChnlMobVerify {

    @Column(name = "BT", length = 1)
    private String bt;

}

