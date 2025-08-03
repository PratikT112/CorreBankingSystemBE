package com.pratikt112.correbankingsystembe.repo;


import com.pratikt112.correbankingsystembe.model.Cmob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmobRepo extends JpaRepository<Cmob, String> {


    @Query("SELECT cm from Cmob cm where cm.custNo = :custNo")
    List<Cmob> findAllByCustNo(String custNo);
}
