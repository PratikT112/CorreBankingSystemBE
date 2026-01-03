package com.pratikt112.correbankingsystembe.service;



import com.pratikt112.banking.event.MobileVerificationEventRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerMobileEventProducer {
    private final KafkaTemplate<String, MobileVerificationEventRecord> kafkaTemplate;

    public CustomerMobileEventProducer(KafkaTemplate<String, MobileVerificationEventRecord> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishCustomerCreated(MobileVerificationEventRecord rec){
        kafkaTemplate.send("customer.mobile.created",
                rec.getCustNo(),
                rec
        );
        log.info("Kafka Mobile Verification request sent for customer : {}", rec.getCustNo());
    }
}
