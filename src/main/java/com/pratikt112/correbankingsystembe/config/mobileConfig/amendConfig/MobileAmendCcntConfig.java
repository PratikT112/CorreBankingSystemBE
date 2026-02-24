package com.pratikt112.correbankingsystembe.config.mobileConfig.amendConfig;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "mobile.amendment.ccnt")
public class MobileAmendCcntConfig {

    private boolean live;

    @Min(0)
    @Max(999)
    private int maxCount;

    @Min(1)
    private int days;
}
