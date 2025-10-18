package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.cuid.Cuid;
import com.pratikt112.correbankingsystembe.model.cuid.CuidId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CuidRepo extends JpaRepository<Cuid, CuidId> {
    @Query("SELECT c FROM Cuid c where c.id.instNo = :socNo and c.id.custNo = :custNo")
    List<Cuid> getBySocNoAndCustNo(@Param("socNo") String socNo, @Param("custNo") String custNo);


    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cuid c where c.id.instNo = :socNo and c.id.custNo = :custNo and c.idMain = 'Y'")
    boolean mainIdExists(@Param("socNo") String instNo, @Param("custNo") String custNo);

}
