package com.pratikt112.correbankingsystembe.service;


import ch.qos.logback.core.util.Loader;
import com.pratikt112.correbankingsystembe.config.mobileConfig.amendConfig.MobileAmendCcntConfig;
import com.pratikt112.correbankingsystembe.exception.MobileAmendmentRestrictedException;
import com.pratikt112.correbankingsystembe.model.ccnt.Ccnt;
import com.pratikt112.correbankingsystembe.model.ccnt.CcntId;
import com.pratikt112.correbankingsystembe.repo.CcntRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class CcntService {


    private static final Logger LOGGER = LoggerFactory.getLogger(CcntService.class);

    private final CcntRepo ccntRepo;
    private final MobileAmendCcntConfig mobileAmendCcntConfig;


    public CcntService(CcntRepo ccntRepo, MobileAmendCcntConfig mobileAmendCcntConfig) {
        this.ccntRepo = ccntRepo;
        this.mobileAmendCcntConfig = mobileAmendCcntConfig;
    }


    public Ccnt saveCcnt(String socNo, String cifNo){
        if(ccntRepo.existsById(new CcntId(socNo, cifNo))){
            Optional<Ccnt> fetched = ccntRepo.findById(new CcntId(socNo, cifNo));
            return null;
        } else {
            addNewCcnt(socNo, cifNo);
            return null;
        }
    }

    private void addNewCcnt(String socNo, String cifNo){
        Ccnt newCcnt = new Ccnt();
        newCcnt.setId(new CcntId(socNo, cifNo));
        newCcnt.setMobAmendStatus("00");
        newCcnt.setMobAmendCount(1);
        newCcnt.setLstMobAmendDate(LocalDate.now());
        ccntRepo.save(newCcnt);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void processCcnt(String socNo, String custNo) {
        Optional<Ccnt> ccntFetched = ccntRepo.findById(new CcntId(socNo, custNo));

        if(ccntFetched.isEmpty()){
            Ccnt newCcnt = new Ccnt(new CcntId(socNo, custNo), "00", 1, LocalDate.now());
            Ccnt save = ccntRepo.save(newCcnt);
            return;
        }

        if(!"00".equals(ccntFetched.get().getMobAmendStatus())){
            return;
        }
        LocalDate today = LocalDate.now();
        LocalDate lastDate = ccntFetched.get().getLstMobAmendDate();
        int max = mobileAmendCcntConfig.getMaxCount();
        int windowDays = mobileAmendCcntConfig.getDays();
        if(ChronoUnit.DAYS.between(lastDate, today) < windowDays){
            if(ccntFetched.get().getMobAmendCount() < max){
                LOGGER.info("Proceeding with CCNT processing for CIF no: {}", custNo);
            } else {
                LOGGER.error("Restricting mobile amendment due to CCNT limit breach for customer: {}", custNo);
                throw new MobileAmendmentRestrictedException("7342",
                        "Max Mobile Amendment Limit Breached",
                        "CCNT_MOB_AMEND_LIMIT_BREACHED",
                        "Maximum mobile amendments already performed within: " + windowDays + " days");
            }
        } else if (ChronoUnit.DAYS.between(lastDate, today) >= max) {
            LOGGER.info("Resetting Mobile Amend count to 1 since amendment attempted outside of time window");
            ccntFetched.get().setLstMobAmendDate(LocalDate.now());
            ccntFetched.get().setMobAmendCount(1);
        }
    }
}
