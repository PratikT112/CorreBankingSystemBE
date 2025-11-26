package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.model.cusm.Cusm;
import com.pratikt112.correbankingsystembe.processor.CustomerProcessingRule;
import com.pratikt112.correbankingsystembe.repo.CusmRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CustomerOrchestratorService {
    private final List<CustomerProcessingRule> processors;

    @Autowired
    CifGeneratorService cifGeneratorService;

    public CustomerOrchestratorService(List<CustomerProcessingRule> processors) {
        this.processors = processors;
    }

    @Transactional
    public void createCustomer(CobData cobData){
        String newCIF = cifGeneratorService.generateCif();
        log.info("Generated new CIF: {}", newCIF);

        for(CustomerProcessingRule processor: processors){
            if(processor.supports(cobData, newCIF)){
                log.info("Processing CIF: {} for {}", newCIF, processor.getProcessorName());
                processor.process(cobData, newCIF);
            }
        }
    }

    public void amendCustomer(CobData newCobData){
        log.info("Code to be written");
    }

}
