package com.pratikt112.correbankingsystembe.model.usty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;


@Entity
@Table(name = "USTY")

public class Usty implements Serializable {

    @Id
    @Column(name = "USER_TYPE_CODE", nullable = false, length = 2)
    private String userTypeCode;

    @Column(name = "DESCRIPTION", length = 50, nullable = false)
    private String description;


    @Override
    public int hashCode() {
        return Objects.hash(userTypeCode);
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(o == null) return false;

        if(getClass() != obj.getClass()) return false;

        Usty other = (Usty) obj;
        return Objects.equals(this.userTypeCode, other.userTypeCode);
     }


    @Override
    public String toString() {
        return "Usty{" +
                "userTypeCode='" + userTypeCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
