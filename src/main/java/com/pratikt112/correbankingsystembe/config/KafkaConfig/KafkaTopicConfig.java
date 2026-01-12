package com.pratikt112.correbankingsystembe.config.KafkaConfig;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@ConditionalOnProperty(
        name = "spring.kafka.enabled",
        havingValue = "true",
        matchIfMissing = true
)
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic mobileVerificationTopic(){
        return TopicBuilder.name("customer.mobile.created")
                .build();
    }
}
