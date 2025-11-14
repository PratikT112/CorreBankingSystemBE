package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.repo.AccountSequenceRepository;
import com.pratikt112.correbankingsystembe.repo.CifSequenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountGeneratorService {
    private final AccountSequenceRepository accountSequenceRepository;
    private final CifSequenceRepository cifSequenceRepository;

    public AccountGeneratorService(AccountSequenceRepository accountSequenceRepository, CifSequenceRepository cifSequenceRepository) {
        this.accountSequenceRepository = accountSequenceRepository;
        this.cifSequenceRepository = cifSequenceRepository;
    }

    @Transactional
    public String generateAccount() {
        long nextVal = accountSequenceRepository.getNextAccountValue();
        return String.format("%016d", nextVal);
    }
}
