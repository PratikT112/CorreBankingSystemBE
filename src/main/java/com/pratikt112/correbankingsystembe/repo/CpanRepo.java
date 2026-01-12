package com.pratikt112.correbankingsystembe.repo;

import com.pratikt112.correbankingsystembe.model.cpan.Cpan;
import com.pratikt112.correbankingsystembe.model.cpan.CpanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CpanRepo extends JpaRepository<Cpan, CpanId> {

    @Query(value = "SELECT c.id.custNo from Cpan c where c.custPanNo = :custPanNo")
    String getExistingCustomerByCustPanNo(String custPanNo);
}
