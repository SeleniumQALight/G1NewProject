package api;

import io.restassured.http.ContentType;
import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;


import static io.restassured.RestAssured.given;

public class CurrencyHelper {
    Logger logger = Logger.getLogger(getClass());

    public CurrencyDTO[] getCurrencyListByApiUsdEuroRubBtcByUAH() {

        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.CURRENCY, 5)
                .then()
                .statusCode(200).log().all()
                .extract().response().as(CurrencyDTO[].class);
        Assert.assertEquals("Response body length is not correct -" + responseBody.length, 4, responseBody.length);

        return responseBody;


    }

    public void saveCurseByValueCurrency(String currency) {

        CurrencyDTO[] responseBody = getCurrencyListByApiUsdEuroRubBtcByUAH();

        for (CurrencyDTO currencyDTO : responseBody) {

            if (currency.equals(currencyDTO.getCcy())) {

                TestData.RATE_CURRENCY_BY_API = currencyDTO.getBuy();
                logger.info(TestData.RATE_CURRENCY_BY_API);
                TestData.RATE_CURRENCY_SALE_API = currencyDTO.getSale();
                logger.info(TestData.RATE_CURRENCY_SALE_API);
            }
        }

    }


}





