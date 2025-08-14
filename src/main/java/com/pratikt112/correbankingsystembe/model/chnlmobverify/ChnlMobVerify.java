package com.pratikt112.correbankingsystembe.model.chnlmobverify;

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

    @Id
    @Column(name = "BT", length = 5)
    private String bt;

}

