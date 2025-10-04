package com.pratikt112.correbankingsystembe.config;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SystemDateProvider {
    private final LocalDate systemDate;

    public SystemDateProvider(){
        this.systemDate = LocalDate.now();
    }

    public LocalDate getSystemDate(){
        return systemDate;
    }
}
