package com.pratikt112.correbankingsystembe.kafka;


import com.pratikt112.banking.event.MobileVerificationEventRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class CustomerMobileEventProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerMobileEventProducer.class);

    private KafkaTemplate<String, MobileVerificationEventRecord> kafkaTemplate;

    public CustomerMobileEventProducer(KafkaTemplate<String, MobileVerificationEventRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(MobileVerificationEventRecord rec){

        Message<MobileVerificationEventRecord> message = MessageBuilder
                .withPayload(rec)
                .setHeader(KafkaHeaders.TOPIC, "customer.mobile.created")
                .build();

        kafkaTemplate.send(message);
        LOGGER.info("Verification request sent to NE for customer: {}", rec.getCustNo());
    }
}
