package com.pratikt112.correbankingsystembe.processor;

import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.model.turn.Turn;
import com.pratikt112.correbankingsystembe.model.turn.TurnId;
import com.pratikt112.correbankingsystembe.service.TurnService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;


@Service
@Order(8)
public class TurnProcessor implements CustomerProcessingRule{

    private final TurnService turnService;


    public TurnProcessor(TurnService turnService){
        this.turnService = turnService;
    }

    @Override
    public String getProcessorName() {
        return "TURN processor";
    }

    @Override
    public boolean supports(CobData cobData, String newCIF) {
        return true;
    }

    @Override
    public void process(CobData cobData, String newCIF) {
        Turn constructed = constructTurnFromCobData(cobData, newCIF);
        turnService.saveTurn(constructed);
    }

    private Turn constructTurnFromCobData(CobData cobData, String newCIF) {
        Turn constructed = new Turn();
        constructed.setId(new TurnId("003", newCIF));
        constructed.setTurnOver(cobData.getCustAnnIncome());
        constructed.setConsentData(cobData.getCustConsent());
        constructed.setConsentDataDt(cobData.getCustConsentDate());
        return constructed;
    }
}
