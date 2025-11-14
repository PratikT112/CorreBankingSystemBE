package com.pratikt112.correbankingsystembe.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountSequenceRepository {

    private final JdbcTemplate jdbcTemplate;

    public AccountSequenceRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long getNextAccountValue(){
        Long current = jdbcTemplate.queryForObject(
                "SELECT NEXT_VAL FROM ACCOUNT_SEQUENCE WHERE SEQ_NAME = 'GLOBAL_ACCOUNT' FOR UPDATE",
                Long.class
        );
        long nextVal = current + 1;

        jdbcTemplate.update("UPDATE ACCOUNT_SEQUENCE SET NEXT_VAL = ? WHERE SEQ_NAME = 'GLOBAL_ACCOUNT'",
                nextVal);

        return current;
    }
}
