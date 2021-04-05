package apiTests;

import api.Endpoints;
import api.ExchangeRateDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class PrivatBankHomeworkTest {

    final private Logger LOG = Logger.getLogger(getClass());

    @Test
    public void getPBExchangeRatesTest() {
        ExchangeRateDTO[] response = given()
                .contentType(ContentType.JSON)
                .log().uri()
                .filter(new AllureRestAssured())
                .when()
                .get(Endpoints.PB_GET_EXCH_RATES)
                .then()
                .statusCode(200)
                .log().ifValidationFails()
                .extract()
                .as(ExchangeRateDTO[].class);

        Arrays.stream(response).forEach(val -> LOG.info(String.format(
                "Exchange rate of %s to %s:->  buy: %s sell: %s",
                val.getCcy(),val.getBase_ccy(),val.getBuy(),val.getSale())));
    }
}
