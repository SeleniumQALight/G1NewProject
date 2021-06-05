package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivatbank {

    private final static Logger LOG = Logger.getLogger("PB_apiHelper");

    public static ExchangeRateDTO[] getExchangeRatesPB(){
        LOG.info("Requesting exchange rates from PB API");
        return given()
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
    }
}
