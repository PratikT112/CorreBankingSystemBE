package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.cusm.Cusm;
import com.pratikt112.correbankingsystembe.model.cusm.CusmId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CusmRepo extends JpaRepository<Cusm, CusmId> {

}
