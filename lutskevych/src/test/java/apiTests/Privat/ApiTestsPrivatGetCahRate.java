package apiTests.Privat;

import api.CashRateDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static api.EndPoints.GET_CASH_RATE;
import static io.restassured.RestAssured.given;

public class ApiTestsPrivatGetCahRate {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getCaseRate(){
        CashRateDTO[] responseBody =
                given()
                        .contentType(ContentType.JSON).log().all()
                        .filter(new AllureRestAssured())
                .when()
                        .get(GET_CASH_RATE)
                .then()
                        .statusCode(200).log().all()
                        .extract()
                        .response().as(CashRateDTO[].class);

        CashRateDTO[] expectedListCashRateDTO = {
                new CashRateDTO("USD","UAH", "27.55000","27.95000"),
                new CashRateDTO("EUR","UAH", "32.25000","32.85000"),
                new CashRateDTO( "RUR","UAH","0.35700", "0.38700"),
                new CashRateDTO("BTC","USD","55356.5681", "61183.5753")
        };
        logger.info(expectedListCashRateDTO[0].toString());
        Assert.assertEquals(responseBody.length, expectedListCashRateDTO.length);

        SoftAssertions softAssertions = new SoftAssertions();

//        for (int i = 0; i < expectedListCashRateDTO.length; i++) {
//            softAssertions.assertThatt(expectedListCashRateDTO[i]).
//            softAssertions.assertThat(expectedListCashRateDTO[i]).isEqualToIgnoringGivenFields(responseBody[i], "_id", "createdDate", "author");
//            softAssertions.assertThat(expectedListCashRateDTO[i].getAuthor()).isEqualToIgnoringGivenFields(responseBody[i].getAuthor(), "avatar");
//        }
//        logger.info("Cash rate for RUR " +  +  )
//        softAssertions.assertAll();
    }
}
