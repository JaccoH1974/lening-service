package nl.bank.leningservice.interfaces.levensverzekering;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Levensverzekering {
    private Double verzekerdkapitaal;
    private LocalDate geboortedatum ;
    private Integer looptijd;
    private Double premie;

}
