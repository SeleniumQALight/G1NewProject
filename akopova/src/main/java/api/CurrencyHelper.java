package api;

import io.restassured.http.ContentType;
import libs.TestData;
import org.apache.log4j.Logger;

import static api.EndPoints.currencyExchangeCashJson;
import static io.restassured.RestAssured.given;


public class CurrencyHelper {

    Logger logger = Logger.getLogger(getClass());

        public CurrencyDTO[] currencyRateViaAPI(){

        CurrencyDTO[] expectedResponseCurrencyBody = given()
                .contentType(ContentType.JSON).log().all()
                .when()
                .get(currencyExchangeCashJson)
                .then()
                .statusCode(200).log().all()
                .extract().as(CurrencyDTO[].class);

            return expectedResponseCurrencyBody;
    }
    public void getCurrencyRateViaAPI(String currency) {
        CurrencyDTO[] responseBody = currencyRateViaAPI();

        for (CurrencyDTO currencyDTO : responseBody) {

            if (currency.equals(currencyDTO.getCcy())) {

                TestData.API_RATE_BUY = currencyDTO.getBuy();
                //logger.info("Currency Buy Rate by API " + currency+":" +TestData.API_RATE_BUY);
                TestData.API_RATE_SELL = currencyDTO.getSale();
                //logger.info("Currency Sale Rate by API " + currency+":" +TestData.API_RATE_SELL);
            }
        }

    }


}
