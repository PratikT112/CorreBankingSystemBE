package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.mbex.Mbex;
import com.pratikt112.correbankingsystembe.model.mbex.MbexId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MbexRepo extends JpaRepository<Mbex, MbexId> {
}
