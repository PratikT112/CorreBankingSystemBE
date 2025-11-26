package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.cusvaa.Cusvaa;
import com.pratikt112.correbankingsystembe.model.cusvaa.CusvaaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CusvaaRepo extends JpaRepository<Cusvaa, CusvaaId> {
}
