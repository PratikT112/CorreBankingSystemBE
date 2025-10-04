package com.pratikt112.correbankingsystembe.processor;

import com.pratikt112.correbankingsystembe.DTOs.CobData;

public interface CustomerProcessingRule {
    boolean supports(CobData cobData);
    void process(CobData cobData);
}
