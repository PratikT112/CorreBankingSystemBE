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
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;


@Service
@Order(4)
@Validated
public class CuidProcessor implements CustomerProcessingRule{
    private final CuidRepo cuidRepo;
    private final CuidService cuidService;

    @Autowired
    public CuidProcessor(CuidRepo cuidRepo, CuidService cuidService, CmobService cmobService){
        this.cuidRepo = cuidRepo;
        this.cuidService = cuidService;
    }

    @Override
    public String getProcessorName() {
        return "CUID processor";
    }

    @Override
    public boolean supports(CobData cobData, String newCIF) {
        return true;
    }

    @Override
    public void process(CobData cobData, String newCIF) {
        List<Cuid> constructed = constructCuidListFromCobData(cobData, newCIF);
        if(constructed.size() > 1){
            cuidService.saveCuid(constructed);
        } else {
            cuidService.saveCuid(constructed.getFirst());
        }
    }

    /**
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
    }**/

    static List<Cuid> constructCuidListFromCobData(CobData cobData, String newCIF){
        List<Cuid> constructedCuids = new ArrayList<Cuid>();
        List<OvdDetails> dtoOvdList = cobData.getCustOvdDetails();
        for(OvdDetails entry: dtoOvdList){
            Cuid constructed = new Cuid();
            constructed.setId(new CuidId("003", newCIF, entry.getOvdDocType()));
            constructed.setIdNumber(entry.getOvdDocNumber());
            constructed.setIdExpiryDate(entry.getOvdDocExpiryDate());
            constructed.setIdIssueAt(entry.getOvdDocIssuedAt());
            constructed.setIdRemark(entry.getOvdDocRemark());
            constructed.setIdMain(entry.getOvdDocMain());
            constructed.setIdIssueDate(entry.getOvdDocIssueDate());
            constructedCuids.add(constructed);
        }
        return constructedCuids;
    }
}
