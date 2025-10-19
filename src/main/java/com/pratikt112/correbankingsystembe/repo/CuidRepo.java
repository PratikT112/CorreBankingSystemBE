package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.cuid.Cuid;
import com.pratikt112.correbankingsystembe.model.cuid.CuidId;
import jakarta.validation.constraints.Size;
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


    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cuid c where c.id.idType = :idType and c.idNumber = :idNumber")
    boolean IdAlreadyExists(@Param("idType") String idType, @Param("idNumber") @Size(max = 24, message = "idNumber must be at most 24 characters") String idNumber);

    @Query(value = "SELECT SUBSTRING(c.id.custNo, LENGTH(c.id.custNo) - 9, 10 )  FROM Cuid c WHERE c.id.idType = :idType AND c.idNumber = :idNumber")
    List<String> IdAlreadyExistsCIF(@Param("idType") String idType, @Param("idNumber") @Size(max = 24, message = "idNumber must be at most 24 characters") String idNumber);
}
