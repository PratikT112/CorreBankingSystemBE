package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.mbex.Mbex;
import com.pratikt112.correbankingsystembe.model.mbex.MbexId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MbexRepo extends JpaRepository<Mbex, MbexId> {

    @Query("select m.mobExpDt from Mbex m where m.id = :id")
    Optional<String> getMobExpDtById(@Param("id") MbexId id);
}
