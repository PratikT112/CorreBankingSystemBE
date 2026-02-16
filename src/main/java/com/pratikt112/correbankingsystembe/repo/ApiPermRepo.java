package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.apiPerm.ApiPerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiPermRepo extends JpaRepository<ApiPerm, String> {
}
