package nl.bank.leningservice.interfaces.levensverzekering;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.core.HttpHeaders;

@FeignClient (name = "levensverzekering-service", url="http://${levensverzekering-service.host}:${levensverzekering-service.port}")
public interface LevensVerzekeringRestClient {

        @RequestMapping(method = RequestMethod.GET, value = "/bank/verzekering/leven", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
        Double getPremie(@RequestParam("verzekerdkapitaal") String verzekerdkapitaal,
                         @RequestParam("geboortedatum")String geboortedatum,
                         @RequestParam("looptijd") String looptijd);

}
