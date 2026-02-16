package com.pratikt112.correbankingsystembe.repo;


import com.pratikt112.correbankingsystembe.DTOs.CustMobIsd;
import com.pratikt112.correbankingsystembe.model.cmob.Cmob;
import com.pratikt112.correbankingsystembe.model.cmob.CmobId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CmobRepo extends JpaRepository<Cmob, CmobId> {


//    @Query("SELECT cm from Cmob cm where cm.custNo = :custNo")
//    List<Cmob> findAllByCustNo(String custNo);

//        private int existingCustomersWithMobile()

    List<Cmob> searchCmobsById(CmobId id);

    Optional<List<Cmob>> findByIdSocNoAndIdCustNo(String socNo, String custNo);

    List<Cmob> findByIdSocNoAndIdCustNoAndIsdCodeAndCustMobNo(String socNo, String custNo, String isdCode, String custMobNo);

    List<Cmob> id(CmobId id);


    @Modifying
    @Transactional
    @Query("UPDATE Cmob c set c.verifyFlag = com.pratikt112.correbankingsystembe.enums.VerifyFlag.Y, c.dov = :dov where c.id.socNo = :socNo and c.id.custNo = :custNo and c.id.identifier = :identifier and c.custMobNo = :mobileNumber and c.isdCode = :isdCode")
    int verifyMobileNumber(@Param("socNo") String socNo,
                           @Param("custNo") String custNo,
                           @Param("identifier") String identifier,
                           @Param("mobileNumber") String mobileNumber,
                           @Param("isdCode") String isdCode,
                           @Param("dov") LocalDate dov);


    @Query("SELECT new com.pratikt112.correbankingsystembe.DTOs.CustMobIsd(c.id.custNo, c.custMobNo, c.isdCode) FROM Cmob c")
    Page<CustMobIsd> getCustMobIsd(Pageable pageable);
}
