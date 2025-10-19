package com.pratikt112.correbankingsystembe.config;

import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SystemDateProvider {
    @Getter
    static LocalDate systemDate;

    public SystemDateProvider(){
        systemDate = LocalDate.now();
    }
}
