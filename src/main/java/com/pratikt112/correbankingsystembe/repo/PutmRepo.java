package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.putm.Putm;
import com.pratikt112.correbankingsystembe.model.putm.PutmId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PutmRepo extends JpaRepository<Putm, PutmId> {
}
