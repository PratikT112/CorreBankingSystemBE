package com.pratikt112.correbankingsystembe.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.internal.util.actions.LoadClass;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileVerificationEventRecord {
    private String custNo;
    private String mobileNumber;
    private String isdCode;
    private LocalDateTime createdAt;
}
