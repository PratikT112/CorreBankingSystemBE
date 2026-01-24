package com.pratikt112.correbankingsystembe.DTOs;


import com.pratikt112.correbankingsystembe.validation.annotation.Adult;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TellerCreateRequest {

    @NotBlank(message = "TELLER_NO is required")
    @Pattern(regexp = "\\d+", message = "TELLER_NO must contain only numeric digits")
    @Size(min = 3, max = 7, message = "TELLER_NO must be between 3 and 7 characters")
    private String tellerNo;

    @NotBlank(message = "TELLER_NAME is required")
    @Size(max = 20, message = "TELLER_NAME cannot exceed 20 characters")
    private String tellerName;

    @NotNull(message = "TELLER_DOB is required")
    @Past(message = "DOB cannot be a future date")
    @Adult(minAge = 22)
    private LocalDate tellerDOB;

    @NotBlank(message = "PASSWORD is required")
    @Size(min = 8, max = 18)
    private String tellerPassword;

    @NotBlank(message = "BRANCH_NO is required")
    @Pattern(regexp = "\\d+", message = "BRANCH_NO must contain only numeric digits")
    @Size(max = 5, min = 5)
    private String brchNo;

    @Size(max = 2)
    @Pattern(regexp = "\\d+", message = "CAPABLE must contain only numeric digits")
    private String capable;

    @NotBlank(message = "USER_TYPE is required")
    @Pattern(regexp = "\\d+", message = "USER_TYPE must contain only numeric digits")
    @Size(max = 2)
    private String userType;

    @Size(max = 1)
    private String tellerIdentifier;
}
