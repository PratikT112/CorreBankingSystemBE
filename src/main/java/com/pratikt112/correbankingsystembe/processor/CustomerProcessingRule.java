package com.pratikt112.correbankingsystembe.processor;

import com.pratikt112.correbankingsystembe.DTOs.CobData;


public interface CustomerProcessingRule {
    boolean supports(CobData cobData, String newCIF);
    void process(CobData cobData, String newCIF);
}
