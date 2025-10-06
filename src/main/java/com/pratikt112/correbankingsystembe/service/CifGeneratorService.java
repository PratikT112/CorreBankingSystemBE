package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.repo.CifSequenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CifGeneratorService {
    private final CifSequenceRepository cifSequenceRepository;

    public CifGeneratorService(CifSequenceRepository cifSequenceRepository){
        this.cifSequenceRepository = cifSequenceRepository;
    }

    @Transactional
    public String generateCif(){
        long nextVal = cifSequenceRepository.getNextCifValue();
        return String.format("%016d", nextVal);
    }
}
