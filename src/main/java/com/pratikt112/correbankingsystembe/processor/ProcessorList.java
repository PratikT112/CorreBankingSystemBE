package com.pratikt112.correbankingsystembe.processor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@Component
public class ProcessorList {

    @Autowired
    CusmProcessor cusmProcessor;

    @Autowired
    Form60Processor form60Processor;

    @Autowired
    CmobProcessor cmobProcessor;

    @Autowired
    CuidProcessor cuidProcessor;

    @Autowired
    CusvaaProcessor cusvaaProcessor;

    @Autowired
    CusvddProcessor cusvddProcessor;

    @Autowired
    PsofProcessor psofProcessor;

    private List<CustomerProcessingRule> processingRules = new ArrayList<>();

    public ProcessorList(){
        processingRules.add(cusmProcessor);
        processingRules.add(form60Processor);
        processingRules.add(cmobProcessor);
        processingRules.add(cuidProcessor);
        processingRules.add(cusvaaProcessor);
        processingRules.add(cusvddProcessor);
        processingRules.add(psofProcessor);
    }
}
