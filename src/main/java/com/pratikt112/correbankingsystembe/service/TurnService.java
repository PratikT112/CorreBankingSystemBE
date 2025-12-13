package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.exception.DuplicateRecordException;
import com.pratikt112.correbankingsystembe.exception.ValidationException;
import com.pratikt112.correbankingsystembe.model.turn.Turn;
import com.pratikt112.correbankingsystembe.model.turn.TurnId;
import com.pratikt112.correbankingsystembe.repo.TurnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Objects;

@Service
public class TurnService {

    private final TurnRepo turnRepo;

    @Autowired
    public TurnService(TurnRepo turnRepo){
        this.turnRepo = turnRepo;
    }

    public Turn saveTurn(Turn newTurn){
        validateTurn(newTurn);
        return turnRepo.save(newTurn);
    }

    private void validateTurn(Turn newTurn) {
        if(Objects.equals(newTurn.getConsentData(), "Y") && newTurn.getConsentDataDt() == null){
            throw new ValidationException("CONSENT_EXCEPTION",
                    "Date of consent is required if consent is provided",
                    "Date of consent is required if consent is provided");
        }
        if(Objects.equals(newTurn.getConsentData(), "N") && newTurn.getConsentDataDt() != null){
            throw new ValidationException("CONSENT_EXCEPTION",
                    "Date of consent can only be provided if consent is provided",
                    "Date of consent can only be provided if consent is provided");
        }
        if(turnRepo.existsById(new TurnId("003", newTurn.getId().getCustNo()))){
            throw new DuplicateRecordException("TURN", newTurn.getId().getCustNo());
        }
    }
}
