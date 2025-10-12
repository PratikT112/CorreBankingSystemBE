package com.pratikt112.correbankingsystembe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "IF3501")
public class If3501 implements Serializable {
    @Id
    @Column(name = "BRANCH_CODE", length = 5)
    private String BranchCode;
}
