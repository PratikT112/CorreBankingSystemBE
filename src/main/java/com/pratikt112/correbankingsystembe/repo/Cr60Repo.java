package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.cr60.Cr60;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Cr60Repo extends JpaRepository<Cr60, String> {
}
