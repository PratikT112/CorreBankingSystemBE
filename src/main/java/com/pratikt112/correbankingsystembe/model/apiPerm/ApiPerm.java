package com.pratikt112.correbankingsystembe.model.apiPerm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;


@Entity
@Table(name = "API_PERM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiPerm implements Serializable {

    @Id
    @Column(name = "PERM_CODE", nullable = false, length = 50)
    @Size(min = 5, max = 50)
    @NotBlank
    private String permCode;

    @Column(name = "DESCRIPTION", length = 200, nullable = false)
    @NotBlank
    @Size(max = 200)
    private String description;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;

        if(getClass() != obj.getClass()) return false;

        ApiPerm other = (ApiPerm) obj;
        return Objects.equals(this.permCode, other.permCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permCode);
    }
}
