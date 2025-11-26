package com.pratikt112.correbankingsystembe.processor;

import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.config.SystemDateProvider;
import com.pratikt112.correbankingsystembe.model.cusvaa.Cusvaa;
import com.pratikt112.correbankingsystembe.model.cusvaa.CusvaaId;
import com.pratikt112.correbankingsystembe.repo.CusvaaRepo;
import com.pratikt112.correbankingsystembe.service.CusvaaService;
import com.zaxxer.hikari.HikariConfigMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Order(5)
public class CusvaaProcessor implements CustomerProcessingRule{
    private final CusvaaRepo cusvaaRepo;
    private final CusvaaService cusvaaService;

    @Autowired
    public CusvaaProcessor(CusvaaRepo cusvaaRepo, CusvaaService cusvaaService) {
        this.cusvaaRepo = cusvaaRepo;
        this.cusvaaService = cusvaaService;
    }

    @Override
    public String getProcessorName() {
        return "CUSVAA processor";
    }

    @Override
    public boolean supports(CobData cobData, String newCIF) {
        return true;
    }

    @Override
    public void process(CobData cobData, String newCIF) {
        Cusvaa constructed = constructCusvaaFromCobData(cobData, newCIF);
        cusvaaService.saveCusvaa(constructed);
    }

    static Cusvaa constructCusvaaFromCobData(CobData cobData, String newCIF){
        Cusvaa constructed = new Cusvaa();
        constructed.setId(new CusvaaId("003", newCIF, "1"));
        constructed.setCode("XX");
        constructed.setDeli("N");
        constructed.setTitleCode(cobData.getCustNameMain().getTitle());
        constructed.setName1(cobData.getCustNameMain().getFirstName());
        constructed.setMidName(cobData.getCustNameMain().getMiddleName());
        constructed.setName2(cobData.getCustNameMain().getLastName());
        constructed.setFatherTitle(cobData.getCustFatherName().getTitle());
        constructed.setFatherName(String.join(" ",
                cobData.getCustFatherName().getFirstName(),
                cobData.getCustFatherName().getMiddleName(),
                cobData.getCustFatherName().getLastName()));
        constructed.setMotherTitle(cobData.getCustMotherName().getTitle());
        constructed.setMotherName(String.join(" ",
                cobData.getCustMotherName().getFirstName(),
                cobData.getCustMotherName().getMiddleName(),
                cobData.getCustMotherName().getLastName()));
        constructed.setSpouseTitle(cobData.getCustSpouseName().getTitle());
        constructed.setSpouseName(String.join(" ",
                cobData.getCustSpouseName().getFirstName(),
                cobData.getCustSpouseName().getMiddleName(),
                cobData.getCustSpouseName().getLastName()));
        constructed.setRelativeCode(cobData.getCustRelativeCode());
        constructed.setAdd1(cobData.getCustMainAddressDetails().getAddressLine1());
        constructed.setAdd2(cobData.getCustMainAddressDetails().getAddressLine2());
        constructed.setAdd3(cobData.getCustMainAddressDetails().getAddressLine3());
        constructed.setAdd4(cobData.getCustMainAddressDetails().getCity());
        constructed.setStateCode(cobData.getCustMainAddressDetails().getState());
        constructed.setCityCode(cobData.getCustMainAddressDetails().getCity());
        constructed.setCountryCode(cobData.getCustMainAddressDetails().getCountry());
        constructed.setPostcode(cobData.getCustMainAddressDetails().getPincode());
        constructed.setPhoneNoRes(cobData.getCustTeleRes());
        constructed.setPhoneNoBus(cobData.getCustTeleOff());
        constructed.setFaxNo("");
        constructed.setTelexNo(cobData.getCustMobNo().getMobNo());
        constructed.setEffeDate(SystemDateProvider.getSystemDate());
        constructed.setExpiDate(LocalDate.of(2099, 12, 31));
        return constructed;
    }
}
