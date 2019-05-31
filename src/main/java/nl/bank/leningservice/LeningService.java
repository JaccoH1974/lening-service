package nl.bank.leningservice;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.bank.leningservice.interfaces.levensverzekering.LevensVerzekeringRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
@NoArgsConstructor
public class LeningService {

    @Autowired
    private LevensVerzekeringRestClient restClient;

    public Double calculateMaxLening(Double inkomen) {
        return inkomen * 4;
    }

    public Double calculateTeVerzekerenRisico(Double lening) {
        return lening / 5;
    }

    public Double calculateAnnuiteit(Double geleendbedrag, Integer looptijdInMaanden, Double jaarrente){
        jaarrente = jaarrente /100;
        Double maandrente = jaarrente / 12;
        Double annuiteit = (maandrente / (1- Math.pow((1+maandrente),(-looptijdInMaanden)))) * geleendbedrag;
        return annuiteit;
    }

    public Double getPremie(Double teVerzekerenRisico, Date geboortedatum, Integer looptijdInMaanden) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strGeboortedatum = dateFormat.format(geboortedatum);

        Double premie = restClient.getPremie(teVerzekerenRisico.toString(), strGeboortedatum, looptijdInMaanden.toString());
        return premie;
    }
}
