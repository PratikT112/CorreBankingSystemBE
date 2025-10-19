package com.pratikt112.correbankingsystembe.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CifSequenceRepository {

    private final JdbcTemplate jdbcTemplate;

    public CifSequenceRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long getNextCifValue(){
        Long current = jdbcTemplate.queryForObject(
                "SELECT NEXT_VAL FROM CIF_SEQUENCE WHERE SEQ_NAME = 'GLOBAL_CIF' FOR UPDATE",
                Long.class
        );

        long nextVal = current + 1;

        jdbcTemplate.update("UPDATE CIF_SEQUENCE SET NEXT_VAL = ? WHERE SEQ_NAME = 'GLOBAL_CIF'",
                nextVal);

        return current;
    }
}
