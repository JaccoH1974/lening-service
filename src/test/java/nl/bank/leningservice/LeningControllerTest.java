package nl.bank.leningservice;

import com.github.tomakehurst.wiremock.client.WireMock;
import feign.FeignException;
import nl.bank.leningservice.interfaces.levensverzekering.LevensVerzekeringRestClient;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@ActiveProfiles({"test"})
public class LeningControllerTest {


    @Autowired
    private LeningController controller;

    @Autowired
    private LeningService service;

    @Autowired
    private LevensVerzekeringRestClient restClient;

    @Before
    public void setUp() {

        WireMock.stubFor(WireMock.get(
                WireMock.urlPathEqualTo("/bank/verzekering/leven"))
                .withQueryParam("verzekerdkapitaal", WireMock.equalTo("60000.0"))
                .withQueryParam("geboortedatum", WireMock.equalTo("1999-09-06"))
                .withQueryParam("looptijd", WireMock.equalTo("360"))
                .willReturn(WireMock.aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.SC_OK)
                        .withBody("{\n" +
                                "    \"premie\": 33.5,\n" +
                                "    \"risicoprofiel\": \"risico matig\"\n" +
                                "} ")));
    }

    @Test
    public void testGetMaximumLening() {

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date geboortedatum = format.parse("1999-09-06");
            Double inkomen = 75000d;
            Integer looptijd = 360;
            Double jaarrente = 2.5d;

            Lening lening = new Lening(geboortedatum, 300000d, inkomen, 1185.3626964531566d, 60000d, 33.5);

            ResponseEntity<Lening> response = controller.getMaximumLening(geboortedatum, inkomen, looptijd, jaarrente);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(org.springframework.http.HttpStatus.OK);
            Assertions.assertThat(response.getBody()).isEqualTo(lening);

        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testGetMaximumLening_Jaarrente_Null_Error() {

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date geboortedatum = format.parse("1999-09-06");
            Double inkomen = 75000d;
            Integer looptijd = 360;

            Lening lening = new Lening(geboortedatum, 300000d, inkomen, 1185.3626964531566d, 60000d, 33.5);

            ResponseEntity<Lening> response = controller.getMaximumLening(geboortedatum, inkomen, looptijd, null);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(org.springframework.http.HttpStatus.BAD_REQUEST);

        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testGetMaximumLening_Geboortedatum_Null_Error() {

            Double inkomen = 75000d;
            Integer looptijd = 360;
            Double jaarrente = 2.5d;

            Lening lening = new Lening(null, 300000d, inkomen, 1185.3626964531566d, 60000d, 33.5);

            ResponseEntity<Lening> response = controller.getMaximumLening(null, inkomen, looptijd, jaarrente);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(org.springframework.http.HttpStatus.BAD_REQUEST);

    }

    @Test
    public void testGetMaximumLening_Inkomen_Null_Error() {

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date geboortedatum = format.parse("1999-09-06");
            Integer looptijd = 360;
            Double jaarrente = 2.5d;

            Lening lening = new Lening(geboortedatum, 300000d, null, 1185.3626964531566d, 60000d, 33.5);

            ResponseEntity<Lening> response = controller.getMaximumLening(geboortedatum, null, looptijd, jaarrente);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(org.springframework.http.HttpStatus.BAD_REQUEST);

        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testGetMaximumLening_Looptijd_null_Error() {

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date geboortedatum = format.parse("1999-09-06");
            Double inkomen = 75000d;
            Double jaarrente = 2.5d;

            Lening lening = new Lening(geboortedatum, 300000d, inkomen, 1185.3626964531566d, 60000d, 33.5);

            ResponseEntity<Lening> response = controller.getMaximumLening(geboortedatum, inkomen, null, jaarrente);

            Assertions.assertThat(response.getStatusCode()).isEqualTo(org.springframework.http.HttpStatus.BAD_REQUEST);

        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
