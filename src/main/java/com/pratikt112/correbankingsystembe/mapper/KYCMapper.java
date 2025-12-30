import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.DTOs.OvdDetails;
import com.pratikt112.correbankingsystembe.model.cky1.Cky1;
import com.pratikt112.correbankingsystembe.model.cky1.Cky1Id;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public final class KYCMapper{

    public static Cky1 cobDataToCKY1(CobData cobData, String socNo, String newCIF){
        if(cobData == null || socNo.isBlank() || newCIF.isBlank()) return null;

        Cky1 constructed = new Cky1();
        constructed.setId(new Cky1Id(socNo, newCIF));
        constructed.setKycNo("12345678912345");
        constructed.setPrefix1(cobData.getCustNameMain().getTitle());
        constructed.setMaidenFirstName(cobData.getCustNameMain().getFirstName());
        constructed.setMaidenMidName(cobData.getCustNameMain().getMiddleName());
        constructed.setMaidenLastName(cobData.getCustNameMain().getLastName());
        constructed.setPrefix2(cobData.getCustFatherName().getTitle());
        constructed.setFatherFirstName(cobData.getCustFatherName().getFirstName());
        constructed.setFatherMidName(cobData.getCustFatherName().getMiddleName());
        constructed.setFatherLastName(cobData.getCustFatherName().getLastName());
        constructed.setPrefix3(cobData.getCustMotherName().getTitle());
        constructed.setMotherFirstName(cobData.getCustMotherName().getFirstName());
        constructed.setMotherMidName(cobData.getCustMotherName().getMiddleName());
        constructed.setMotherLastName(cobData.getCustMotherName().getLastName());
        constructed.setCitizenship(cobData.getCustCitizenship());
        constructed.setOccupationType(cobData.getCustOccType());
        OvdDetails main = cobData.getCustOvdDetails().stream().filter(x -> x.getOvdDocMain().equals("Y")).findFirst().get();
        constructed.setIdProof(main.getOvdDocType());
        constructed.setIdNo(main.getOvdDocNumber());
        LocalDate passExpiryDate = cobData
                .getCustOvdDetails()
                .stream()
                .filter(x -> List.of("0004", "0005").contains(x.getOvdDocType()))
                .map(OvdDetails::getOvdDocExpiryDate)
                .findFirst()
                .orElse(null);
        constructed.setPassExpiry(passExpiryDate);
        LocalDate dlExpiryDate = cobData
                .getCustOvdDetails()
                .stream()
                .filter(x -> List.of("0002").contains(x.getOvdDocType()))
                .map(OvdDetails::getOvdDocExpiryDate)
                .findFirst()
                .orElse(null);
        constructed.setDrivExpiry(dlExpiryDate);
        constructed.setIdOther("DUMMY OTHER ID");
        constructed.setDocType("DD");
        constructed.setAddType("MA");
        constructed.setAddProof();
    }
}