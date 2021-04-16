package apiTests;
import api.ExchangeRateDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static api.EndPoints.CURRENCY;
import static io.restassured.RestAssured.given;

public class ApiTestsPrivatGetExchangeRates {
    Logger logger = Logger.getLogger(getClass());
    @Test
    public void getExchangeRate(){
        ExchangeRateDTO[] responseBody =
                given()
                        .param("json")
                        .param("exchange")
                        .param("coursid=5")
                        .log().all()
                        .filter(new AllureRestAssured())
                .when()
                        .get(CURRENCY)
                .then()
                        .statusCode(200).log().all()
                        .extract()
                        .response().as(ExchangeRateDTO[].class);

        ExchangeRateDTO[] expectedListCashRateDTO = {
                new ExchangeRateDTO("USD","UAH", "27.55000","27.95000"),
                new ExchangeRateDTO("EUR","UAH", "32.25000","32.85000"),
                new ExchangeRateDTO( "RUR","UAH","0.35700", "0.38700"),
                new ExchangeRateDTO("BTC","USD","55356.5681", "61183.5753")
        };
        logger.info(expectedListCashRateDTO[0].toString());
        Assert.assertEquals(responseBody.length, expectedListCashRateDTO.length);

        SoftAssertions softAssertions = new SoftAssertions();
    }
}