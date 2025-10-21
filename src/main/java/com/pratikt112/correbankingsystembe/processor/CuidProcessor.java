package com.pratikt112.correbankingsystembe.processor;

import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.DTOs.OvdDetails;
import com.pratikt112.correbankingsystembe.model.cuid.Cuid;
import com.pratikt112.correbankingsystembe.model.cuid.CuidId;
import com.pratikt112.correbankingsystembe.repo.CuidRepo;
import com.pratikt112.correbankingsystembe.service.CmobService;
import com.pratikt112.correbankingsystembe.service.CuidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;


@Service
@Order(4)
public class CuidProcessor implements CustomerProcessingRule{
    private final CuidRepo cuidRepo;
    private final CuidService cuidService;
    private final CmobService cmobService;

    @Autowired
    public CuidProcessor(CuidRepo cuidRepo, CuidService cuidService, CmobService cmobService){
        this.cuidRepo = cuidRepo;
        this.cuidService = cuidService;
        this.cmobService = cmobService;
    }

    @Override
    public String getProcessorName() {
        return "CMOB processor";
    }

    @Override
    public boolean supports(CobData cobData, String newCIF) {
        return true;
    }

    @Override
    public void process(CobData cobData, String newCIF) {
        Cuid constructed = constructCuidFromCobData(cobData, newCIF);
        cuidService.saveCuid(constructed);
    }

    static Cuid constructCuidFromCobData(CobData cobData, String newCIF){
        OvdDetails dtoOvd = cobData.getCustOvdDetails();
        Cuid constructed = new Cuid();
        constructed.setId(new CuidId("003", newCIF, dtoOvd.getOvdDocType()));
        constructed.setIdNumber(dtoOvd.getOvdDocNumber());
        constructed.setIdExpiryDate(dtoOvd.getOvdDocExpiryDate());
        constructed.setIdIssueAt(dtoOvd.getOvdDocIssuedAt());
        constructed.setIdRemark("Dummy Remark");
        constructed.setIdMain("Y");
        constructed.setIdIssueDate(dtoOvd.getOvdDocIssueDate());
        return constructed;
    }
}
