package com.pratikt112.correbankingsystembe.model.activeSession;


import com.pratikt112.correbankingsystembe.model.telm.Telm;
import com.pratikt112.correbankingsystembe.utility.TellerNoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ACTIVE_SESSION",
uniqueConstraints = {
        @UniqueConstraint(
                name = "uq_active_session_teller",
                columnNames = "TELLER_NO"
        )
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActiveSession {

    @EmbeddedId
    private ActiveSessionId id;

    @Column(name = "SOC_NO", nullable = false, length = 3)
    private String socNo;

    @Convert(converter = TellerNoConverter.class)
    @Column(name = "TELLER_NO",length = 16, nullable = false)
    private String tellerNo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(
                    name = "SOC_NO",
                    referencedColumnName = "SOC_NO",
                    insertable = false,
                    updatable = false
            ),
            @JoinColumn(
                    name = "TELLER_NO",
                    referencedColumnName = "TELLER_NO",
                    insertable = false,
                    updatable = false
            )
    })
    private Telm telm;

    @Column(name = "LOGIN_TIME", nullable = false)
    private LocalDateTime loginTime;

    @Column(name = "BRCH_NO",length = 5, nullable = false)
    private String brchNo;

    @Column(name = "EXPIRY_TIME", nullable = false)
    private LocalDateTime expiryTime;
}
