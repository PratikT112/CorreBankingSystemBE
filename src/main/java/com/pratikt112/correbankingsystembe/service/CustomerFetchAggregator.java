package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.DTOs.OvdDetails;
import com.pratikt112.correbankingsystembe.DTOs.PersonName;
import com.pratikt112.correbankingsystembe.model.cmob.Cmob;
import com.pratikt112.correbankingsystembe.model.cmob.CmobId;
import com.pratikt112.correbankingsystembe.model.cr60.Cr60;
import com.pratikt112.correbankingsystembe.model.cuid.Cuid;
import com.pratikt112.correbankingsystembe.model.cuid.CuidId;
import com.pratikt112.correbankingsystembe.model.cusm.Cusm;
import com.pratikt112.correbankingsystembe.model.cusm.CusmId;
import com.pratikt112.correbankingsystembe.model.cusvaa.Cusvaa;
import com.pratikt112.correbankingsystembe.model.cusvaa.CusvaaId;
import com.pratikt112.correbankingsystembe.model.cusvdd.Cusvdd;
import com.pratikt112.correbankingsystembe.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerFetchAggregator {

    private final CusmRepo cusmRepo;
    private final Cr60Repo cr60Repo;
    private final CmobRepo cmobRepo;
    private final CuidRepo cuidRepo;
    private final CusvaaRepo cusvaaRepo;
    private final CusvddRepo cusvddRepo;

    public CobData fetchCobDB(String cifNo, CobData newCobData){

        try {
            Optional<Cusm> fetchedCusm = cusmRepo.findById(new CusmId("003", cifNo));
            Optional<Cr60> fetchedCr60 = cr60Repo.findById("003" + cifNo);
            Optional<Cmob> fetchedCmob = cmobRepo.findById(new CmobId("003", cifNo, "P"));
            Optional<Cuid> fetchedCuid = cuidRepo.findById(new CuidId("003", cifNo, newCobData.getCustOvdDetails().getOvdDocType()));
            Optional<Cusvaa> fetchedCusvaa = cusvaaRepo.findById(new CusvaaId("003", cifNo, "0001"));
            Optional<Cusvdd> fetchedCusvdd = cusvddRepo.findById("003"+cifNo);
        } catch (Exception e) {
            log.error("Fetch failed for some table");
        }
        return null;
    }

    private CobData constructCobToCompare(Cusm fetchedCusm, Cr60 fetchedCr60, Cmob fetchedCmob, Cuid fetchedCuid, Cusvaa fetchedCusvaa, Cusvdd fetchedCusvdd){
        CobData constructed = new CobData();
        constructed.setCustOvdDetails(new OvdDetails(fetchedCuid.getId().getIdType(),
                fetchedCuid.getIdNumber(),
                fetchedCuid.getIdIssueDate(),
                fetchedCuid.getIdExpiryDate(),
                fetchedCuid.getIdIssueAt()));

        constructed.setCustTierType(fetchedCusm.getTierCustType());
        constructed.setResiStatus(fetchedCusm.getResiStatus());
        constructed.setCustNameMain(new PersonName(fetchedCusvaa.getTitleCode(),
                fetchedCusvaa.getName1(),
                fetchedCusvaa.getMidName(),
                fetchedCusvaa.getName2()));
        constructed.setCustDob(fetchedCusvdd.getBirthDate1());




        return constructed;
    }

}
