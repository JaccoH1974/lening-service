package nl.bank.leningservice;

import lombok.extern.slf4j.Slf4j;
import nl.bank.leningservice.interfaces.levensverzekering.Levensverzekering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping(value = "/bank/lening", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LeningController {

    @Autowired
    private LeningService service;

    @RequestMapping
    ResponseEntity<Lening> getMaximumLening(
            @QueryParam("geboortedatum") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date geboortedatum,
            @QueryParam("inkomen") Double inkomen,
            @QueryParam("looptijdInMaanden") Integer looptijdInMaanden,
            @QueryParam("jaarrente") Double jaarrente) {
        if (geboortedatum == null || inkomen == null || looptijdInMaanden == null || jaarrente == null ) {
            return ResponseEntity.badRequest().build();
        } else {

            Double maxLening = service.calculateMaxLening(inkomen);
            Double teVerzekeringRisico = service.calculateTeVerzekerenRisico(maxLening);

            Levensverzekering levensverzekering  = service.getPremie(teVerzekeringRisico, geboortedatum, looptijdInMaanden);
            Double annuiteit = service.calculateAnnuiteit(maxLening, looptijdInMaanden, jaarrente);

            Lening lening = new Lening(geboortedatum, maxLening, inkomen, annuiteit, teVerzekeringRisico, levensverzekering.getPremie());
            return ResponseEntity.ok(lening);
        }
    }

}
