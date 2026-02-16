package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.usty.Usty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UstyRepo extends JpaRepository<Usty, String> {
}
