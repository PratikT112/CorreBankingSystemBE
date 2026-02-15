package com.pratikt112.correbankingsystembe.model.putm;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PUTM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Putm{
    @EmbeddedId
    private PutmId id;

    @Column(name = "STATUS", length = 2, nullable = false)
    private String status;

}
