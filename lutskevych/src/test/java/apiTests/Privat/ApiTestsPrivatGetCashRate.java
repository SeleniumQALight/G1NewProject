package apiTests.Privat;

import api.ApiHelper;
import api.CashRateDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static api.EndPoints.GET_CASH_RATE;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class ApiTestsPrivatGetCashRate {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getCashRate(){
        CashRateDTO[] responseBody =
                given()
                        .param("json")
                        .param("exchange")
                        .param("coursid=5")
                        .log().all()
                        .filter(new AllureRestAssured())
                .when()
                        .get(GET_CASH_RATE)
                .then()
                        .statusCode(200).log().all()
                        .extract()
                        .response().as(CashRateDTO[].class);

//        CashRateDTO[] expectedListCashRateDTO = {
//                new CashRateDTO("USD","UAH", "27.55000","27.95000"),
//                new CashRateDTO("EUR","UAH", "32.25000","32.85000"),
//                new CashRateDTO( "RUR","UAH","0.35700", "0.38700"),
//                new CashRateDTO("BTC","USD","55356.5681", "61183.5753")
//        };
        for (CashRateDTO element : responseBody) {
            logger.info(format("Курс %s к %s покупка %s; продажа %s",
                    element.getCcy(), element.getBase_ccy(), element.getBuy(), element.getSale()));
        }
    }
}
