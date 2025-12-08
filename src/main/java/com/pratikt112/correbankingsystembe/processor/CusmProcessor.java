package com.pratikt112.correbankingsystembe.processor;

import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.config.SystemDateProvider;
import com.pratikt112.correbankingsystembe.model.cusm.Cusm;
import com.pratikt112.correbankingsystembe.model.cusm.CusmId;
import com.pratikt112.correbankingsystembe.repo.CusmRepo;
import com.pratikt112.correbankingsystembe.service.CifGeneratorService;
import com.pratikt112.correbankingsystembe.service.CusmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Order(1)
public class CusmProcessor implements CustomerProcessingRule{

    private final CusmRepo cusmRepo;
    private final CusmService cusmService;
//    private final String newCIF;

    @Autowired
    SystemDateProvider systemDateProvider;

    @Autowired
    public CusmProcessor(CusmRepo cusmRepo, CusmService cusmService/*, String newCIF*/) {
        this.cusmRepo = cusmRepo;
        this.cusmService = cusmService;
//        this.newCIF = newCIF;
    }


    @Override
    public String getProcessorName() {
        return "CUSM processor";
    }

    @Override
    public boolean supports(CobData cobData, String newCIF) {
        return true;
    }

    @Override
    public void process(CobData cobData, String newCIF) {
        Cusm constructed = constructCusmFromCobData(cobData, newCIF);
        cusmService.saveCusm(constructed);
    }

    public Cusm constructCusmFromCobData(CobData cobData, String newCIF){
        Cusm constructed = new Cusm();
        constructed.setId(new CusmId("003", newCIF));
        constructed.setPrimAcct(newCIF);    //To be fixed
        constructed.setThisAcctStatus("0");
        constructed.setMailInd(cobData.getCustEmail()!=null ? "Y":"N");
        constructed.setNoticeInd(cobData.getCustEmail()!=null ? "Y":"N");
        constructed.setCustomerType("01");
        constructed.setMailIndExpDt(SystemDateProvider.getSystemDate());
        constructed.setDomesticRisk("00");
//        constructed.setCrossBorderRisk("00");
        constructed.setLastUsedAcctNo(newCIF);
        constructed.setCustomerStatus("000");
        constructed.setSegmentCode("D");
        constructed.setCreateDt(SystemDateProvider.getSystemDate());
        constructed.setHomeBranchNo("04234");    // To be fixed
//        constructed.setCountryOfRisk(cobData.getCustISO3166CountryJurisResi()); // To be fixed
        constructed.setTierCustType(cobData.getCustTierType());
        constructed.setMislaOrgCode(getMislaFromGender(cobData.getCustGender()));
        constructed.setBsrOrgCode(getBSRFromGender(cobData.getCustGender()));
        constructed.setResiStatus(cobData.getResiStatus());
        constructed.setNationalityCd(cobData.getCustNationality());
        constructed.setLastStatChgDt(SystemDateProvider.getSystemDate());
//        constructed.setDeliveryMode(cobData.getInbDeliveryMode());
        constructed.setCustTaxPan(cobData.getCustPanNo());
//        constructed.setCustVoterId(Objects.equals(cobData.getCustOvdDetails().stream().filter(x->x.getOvdDocType().equals("0002")).findFirst(), "02") ? cobData.getCustOvdDetails().getOvdDocNumber() : " " );
        constructed.setCustEvalFlag("Y");
        constructed.setPostCheckerId("2199690");
        return constructed;
    }

    public String getMislaFromGender(String gender){
        switch (gender) {
            case "M" -> {
                return "41";
            }
            case "F" -> {
                return "42";
            }
            case "T" -> {
                return "999";
            }
        }
        return "";
    }

    public String getBSRFromGender(String gender){
        switch (gender) {
            case "M" -> {
                return "41";
            }
            case "F" -> {
                return "42";
            }
            case "T" -> {
                return "43";
            }
        }
        return "";
    }
}
