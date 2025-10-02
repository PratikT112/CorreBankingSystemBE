package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.ctpm.Ctpm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CtpmRepo extends JpaRepository<Ctpm,Integer> {

    @Query("SELECT c.docReqd FROM Ctpm c WHERE c.tieredCust = :tieredCust")
    String getDocReqdByTieredCust(String tieredCust);

}
