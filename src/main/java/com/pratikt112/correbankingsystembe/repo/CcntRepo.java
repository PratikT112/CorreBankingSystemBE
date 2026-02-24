package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.ccnt.Ccnt;
import com.pratikt112.correbankingsystembe.model.ccnt.CcntId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CcntRepo extends JpaRepository<Ccnt, CcntId> {


}
