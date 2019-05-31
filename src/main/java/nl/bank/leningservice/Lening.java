package nl.bank.leningservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lening {

    private Date geboortedatum;
    private Double maximaalTeLenenBedrag;
    private Double inkomen;
    private Double annuiteit;
    private Double teVerzekerenRisico;
    private Double maandPremieLevensverzekering;

}
