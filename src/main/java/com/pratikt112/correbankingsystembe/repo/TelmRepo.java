package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.telm.Telm;
import com.pratikt112.correbankingsystembe.model.telm.TelmId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TelmRepo extends JpaRepository<Telm, TelmId> {


    List<Telm> id(TelmId id);

    Telm findTelmById(TelmId id);

    TelmId id(TelmId id);
}
