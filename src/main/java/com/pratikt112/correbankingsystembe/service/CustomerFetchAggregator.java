package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.DTOs.OvdDetails;
import com.pratikt112.correbankingsystembe.DTOs.PersonName;
import com.pratikt112.correbankingsystembe.exception.RecordNotFoundException;
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

        Cusm fetchedCusm = cusmRepo.findById(new CusmId("003", cifNo))
                .orElseThrow(()->{
                    log.error("CUSM not found for CIF: {}", cifNo);
                    return new RecordNotFoundException("CUSM", cifNo);
                });
        Cr60 fetchedCr60 = cr60Repo.findById("003" + cifNo)
                .orElseThrow(()->{
                    log.error("CR60 not found for CIF: {}", cifNo);
                    return new RecordNotFoundException("CR60", cifNo);
                });
        Cmob fetchedCmob = cmobRepo.findById(new CmobId("003", cifNo, "P"))
                .orElseThrow(()->{
                    log.error("CMOB not found for CIF: {}", cifNo);
                    return new RecordNotFoundException("CMOB", cifNo);
                });
        Cuid fetchedCuid = cuidRepo.findById(new CuidId("003", cifNo, newCobData.getCustOvdDetails().stream().filter(x->"Y".equals(x.getOvdDocType())).findFirst().map(OvdDetails::getOvdDocType).orElse("")))
                .orElseThrow(()->{
                    log.error("CUID not found for CIF: {}", cifNo);
                    return new RecordNotFoundException("CUID", cifNo);
                });
        Cusvaa fetchedCusvaa = cusvaaRepo.findById(new CusvaaId("003", cifNo, "0001"))
                .orElseThrow(()->{
                    log.error("CUSVAA not found for CIF: {}", cifNo);
                    return new RecordNotFoundException("CUSVAA", cifNo);
                });
        Cusvdd fetchedCusvdd = cusvddRepo.findById("003"+cifNo)
                .orElseThrow(()->{
                    log.error("CUSVDD not found for CIF: {}", cifNo);
                    return new RecordNotFoundException("CUSVDD", cifNo);
                });

    }

    private CobData constructCobToCompare(Cusm fetchedCusm, Cr60 fetchedCr60, Cmob fetchedCmob, Cuid fetchedCuid, Cusvaa fetchedCusvaa, Cusvdd fetchedCusvdd){
        CobData constructed = new CobData();
//        constructed.setCustOvdDetails(new OvdDetails(fetchedCuid.getId().getIdType(),
//                fetchedCuid.getIdNumber(),
//                fetchedCuid.getIdIssueDate(),
//                fetchedCuid.getIdExpiryDate(),
//                fetchedCuid.getIdIssueAt()));

        constructed.setCustTierType(fetchedCusm.getTierCustType());
        constructed.setResiStatus(fetchedCusm.getResiStatus());
        constructed.setCustNameMain(new PersonName(fetchedCusvaa.getTitleCode(),
                fetchedCusvaa.getName1(),
                fetchedCusvaa.getMidName(),
                fetchedCusvaa.getName2()));
        constructed.setCustDob(fetchedCusvdd.getBirthDate1());
        constructed.setCustGender(fetchedCusvdd.getSexCode());
        constructed.setCustMaritalStatus(fetchedCusvdd.getMaritalStatus());
        constructed.setCustFatherName(getPersonNameFromCombinedName(fetchedCusvaa.getFatherTitle(), fetchedCusvaa.getFatherName()));
        constructed.setCustMotherName(getPersonNameFromCombinedName(fetchedCusvaa.getMotherTitle(), fetchedCusvaa.getMotherName()));
        constructed.setCustSpouseName(getPersonNameFromCombinedName(fetchedCusvaa.getSpouseTitle(), fetchedCusvaa.getSpouseName()));
        constructed.setCustNationality(fetchedCusvaa.getCountryCode());
        constructed.setCustOccType(fetchedCusvdd.getOccupationCode());
        constructed.setCustOccSubType(fetchedCusvdd.getOccupDesc());      //To be checked
//        constructed.setCustAnnIncome(fetchedCusva);

        return constructed;
    }

    private PersonName getPersonNameFromCombinedName(String titleCode, String combinedName){
        PersonName name = new PersonName(titleCode, null, null,null);
        String[] s = combinedName.split(" ");
        long count = combinedName.chars().filter(x -> x == ' ')
                .count();
        if(count == 2){
            name.setFirstName(s[0]);
            name.setMiddleName(s[1]);
            name.setLastName(s[2]);
        } else if(count == 1){
            name.setFirstName(s[0]);
            name.setLastName(s[1]);
        } else {
            name.setFirstName(combinedName);
        }
        return name;
    }

}
