package api;

import io.qameta.allure.restassured.AllureRestAssured;
import libs.PrivateTestData;
import libs.TestData;
import org.apache.log4j.Logger;

import static api.EndPoints.GET_CASH_RATE;
import static io.restassured.RestAssured.given;

public class PrivateApiHelper {
    Logger logger = Logger.getLogger(getClass());

    public void getAndSaveCashRate(String currency) {
        CashRateDTO[] responseBody =
                given()
                        .param("json")
                        .param("exchange")
                        .queryParam("coursid", 5)
                        .log().all()
                        .filter(new AllureRestAssured())
                .when()
                        .get(GET_CASH_RATE)
                .then()
                        .statusCode(200).log().all()
                        .extract()
                        .response().as(CashRateDTO[].class);

        for (int i = 0; i <responseBody.length ; i++) {
            if(responseBody[i].getCcy().equals(currency)){
                PrivateTestData.SALE_RATE_API = Float.parseFloat(responseBody[i].getSale());
                PrivateTestData.BUY_RATE_API = Float.parseFloat(responseBody[i].getBuy());
            }

        }
    }

}
