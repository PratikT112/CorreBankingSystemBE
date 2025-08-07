package com.pratikt112.correbankingsystembe.repo;


import com.pratikt112.correbankingsystembe.model.cmob.Cmob;
import com.pratikt112.correbankingsystembe.model.cmob.CmobId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmobRepo extends JpaRepository<Cmob, CmobId> {


//    @Query("SELECT cm from Cmob cm where cm.custNo = :custNo")
//    List<Cmob> findAllByCustNo(String custNo);

//        private int existingCustomersWithMobile()

    List<Cmob> searchCmobsById(CmobId id);

    List<Cmob> findByIdSocNoAndIdCustNo(String socNo, String custNo);

    List<Cmob> findByIdSocNoAndIdCustNoAndIsdCodeAndCustMobNo(String socNo, String custNo, String isdCode, String custMobNo);

}
