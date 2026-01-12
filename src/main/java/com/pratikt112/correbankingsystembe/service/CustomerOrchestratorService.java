package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.banking.event.MobileVerificationEventRecord;
import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.kafka.CustomerMobileEventProducer;
import com.pratikt112.correbankingsystembe.model.cusm.Cusm;
import com.pratikt112.correbankingsystembe.processor.CustomerProcessingRule;
import com.pratikt112.correbankingsystembe.repo.CusmRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CustomerOrchestratorService {
    private final List<CustomerProcessingRule> processors;


    private final CifGeneratorService cifGeneratorService;

    private final CustomerMobileEventProducer customerMobileEventProducer;

    public CustomerOrchestratorService(List<CustomerProcessingRule> processors, CifGeneratorService cifGeneratorService, CustomerMobileEventProducer customerMobileEventProducer) {
        this.processors = processors;
        this.cifGeneratorService = cifGeneratorService;
        this.customerMobileEventProducer = customerMobileEventProducer;
    }

    @Transactional
    public String createCustomer(CobData cobData){
        String newCIF = cifGeneratorService.generateCif();
        log.info("Generated new CIF: {}", newCIF);

        for(CustomerProcessingRule processor: processors){
            if(processor.supports(cobData, newCIF)){
                log.info("Processing CIF: {} for {}", newCIF, processor.getProcessorName());
                processor.process(cobData, newCIF);
            }
        }
        MobileVerificationEventRecord record = new MobileVerificationEventRecord(
                newCIF,
                cobData.getCustMobNo().getMobNo(),
                cobData.getCustMobNo().getIsd(),
                LocalDateTime.now()
        );
        customerMobileEventProducer.sendMessage(record);
        return newCIF;
    }

    public void amendCustomer(CobData newCobData){
        log.info("Code to be written");
    }

}
