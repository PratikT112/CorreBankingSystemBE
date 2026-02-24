package com.pratikt112.correbankingsystembe;

import com.pratikt112.correbankingsystembe.config.mobileConfig.amendConfig.MobileAmendCcntConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ConfigurationPropertiesScan
public class CorreBankingSystemBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorreBankingSystemBeApplication.class, args);
    }

}
