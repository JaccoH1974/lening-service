package nl.bank.leningservice.interfaces.levensverzekering;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Levensverzekering {
    private String risicoprofiel;
    private Double premie;

}
