package com.pratikt112.correbankingsystembe.service;


import com.pratikt112.correbankingsystembe.event.MobileVerificationEventRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerMobileEventProducer {
    private final KafkaTemplate<String, MobileVerificationEventRecord> kafkaTemplate;

    public CustomerMobileEventProducer(KafkaTemplate<String, MobileVerificationEventRecord> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishCustomerCreated(MobileVerificationEventRecord record){
        kafkaTemplate.send("customer.mobile.created",
                record.getCustomerId(),
                record
        );
    }
}
