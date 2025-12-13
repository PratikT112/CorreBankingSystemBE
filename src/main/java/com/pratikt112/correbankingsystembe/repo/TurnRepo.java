package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.turn.Turn;
import com.pratikt112.correbankingsystembe.model.turn.TurnId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TurnRepo extends JpaRepository<Turn, TurnId> {
}
