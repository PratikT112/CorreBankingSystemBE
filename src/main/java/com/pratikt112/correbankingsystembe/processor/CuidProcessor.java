package com.pratikt112.correbankingsystembe.processor;

import com.pratikt112.correbankingsystembe.DTOs.CobData;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;


@Service
@Order(5)
public class CuidProcessor implements CustomerProcessingRule{
    @Override
    public String getProcessorName() {
        return "";
    }

    @Override
    public boolean supports(CobData cobData, String newCIF) {
        return false;
    }

    @Override
    public void process(CobData cobData, String newCIF) {

    }
}
