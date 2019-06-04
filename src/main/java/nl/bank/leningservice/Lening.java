package nl.bank.leningservice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lening {
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date geboortedatum;
    private Double maximaalTeLenenBedrag;
    private Double inkomen;
    private Double annuiteit;
    private Double teVerzekerenRisico;
    private Double maandPremieLevensverzekering;

}
