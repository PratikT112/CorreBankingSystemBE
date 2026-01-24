package com.pratikt112.correbankingsystembe.model.putm;


import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

}
