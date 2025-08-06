package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.mobh.Mobh;
import com.pratikt112.correbankingsystembe.model.mobh.MobhId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobhRepo extends JpaRepository<Mobh, MobhId> {
}
