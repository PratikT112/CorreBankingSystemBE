package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.psof.Psof;
import com.pratikt112.correbankingsystembe.model.psof.PsofId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PsofRepo extends JpaRepository<Psof, PsofId> {
}
