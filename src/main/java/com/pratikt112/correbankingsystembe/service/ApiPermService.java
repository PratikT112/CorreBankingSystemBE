package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.exception.DuplicateRecordException;
import com.pratikt112.correbankingsystembe.model.apiPerm.ApiPerm;
import com.pratikt112.correbankingsystembe.repo.ApiPermRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApiPermService {

    private final ApiPermRepo apiPermRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiPermService.class);

    public ApiPermService(ApiPermRepo apiPermRepo) {
        this.apiPermRepo = apiPermRepo;
    }

    @Transactional
    public ApiPerm saveApiPerm(ApiPerm apiPerm) {
        if(apiPermRepo.existsById(apiPerm.getPermCode())){
            throw new DuplicateRecordException("API_PERM", apiPerm.getPermCode());
        }
        ApiPerm saved = apiPermRepo.save(apiPerm);
        LOGGER.info("API_PERM persisted for Permission code: {}", apiPerm.getPermCode());
        return saved;
    }
}
