import com.pratikt112.correbankingsystembe.model.cuid.CuidId;
import com.pratikt112.correbankingsystembe.utility.DdMmYyyyStringToLocalDateConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUID")
public class Cuid implements Serializable {

    @EmbeddedId
    private CuidId id;

    @Size(max = 24, message = "idNumber must be at most 24 characters")
    @Column(name = "ID_NUMBER", length = 24)
    private String idNumber;


    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "ID_EXPIRY_DATE", length = 8)
    private LocalDate idExpiryDate;

    @Size(max = 30, message = "idIssueAt must be at most 30 characters")
    @Column(name = "ID_ISSUE_AT", length = 30)
    private String idIssueAt;

    @Size(max = 20, message = "idRemark must be at most 20 characters")
    @Column(name = "ID_REMARK", length = 20)
    private String idRemark;

    @Size(max = 1, message = "idMain must be exactly 1 character")
    @Column(name = "ID_MAIN", length = 1)
    private String idMain;

    @Convert(converter = DdMmYyyyStringToLocalDateConverter.class)
    @Column(name = "ID_ISSUE_DATE", length = 8)
    private LocalDate idIssueDate;
}