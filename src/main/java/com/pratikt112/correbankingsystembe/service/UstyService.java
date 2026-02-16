package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.exception.DuplicateRecordException;
import com.pratikt112.correbankingsystembe.model.usty.Usty;
import com.pratikt112.correbankingsystembe.repo.UstyRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UstyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UstyService.class);

    private final UstyRepo ustyRepo;

    public UstyService(UstyRepo ustyRepo) {
        this.ustyRepo = ustyRepo;
    }

    @Transactional
    public Usty saveUsty(Usty usty) {
        if(ustyRepo.existsById(usty.getUserTypeCode())){
            throw new DuplicateRecordException("USTY", usty.getUserTypeCode());
        } else {
            return ustyRepo.save(usty);
        }
    }
}
