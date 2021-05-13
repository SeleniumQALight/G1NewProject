package apiTests;

import api.CurrencyDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static api.EndPoints.currencyExchangeCashJson;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.fail;

public class ApiTestsCurrenciesRates {

    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getCurrenciesRateCashJson(){
        CurrencyDTO[] actualResponseCurrencyBody = given()
                .contentType(ContentType.JSON).log().all()
                .when()
                .get(currencyExchangeCashJson)
                .then()
                .statusCode(200).log().all()
                .extract().as(CurrencyDTO[].class);

        CurrencyDTO[] expectedListResponseCurrency =  {
                new CurrencyDTO("USD", "UAH", "27.65000", "28.05000"),
                new CurrencyDTO("EUR", "UAH", "32.75000", "33.35000"),
                new CurrencyDTO("RUR", "UAH", "0.35200", "0.38200"),
                new CurrencyDTO("BTC", "USD", "56598.7153", "62588.4525")
        };

        for(int i=0; i< expectedListResponseCurrency.length; i++) {
            logger.info("Rate "+ actualResponseCurrencyBody[i].getCcy() +
                    " to " + actualResponseCurrencyBody[i].getBase_ccy() + ". " +
                    " Buy : " + actualResponseCurrencyBody[i].getBuy() +
                    ", Sell : " + actualResponseCurrencyBody[i].getSale());
        }

        Assert.assertEquals(actualResponseCurrencyBody.length, expectedListResponseCurrency.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i=0; i< expectedListResponseCurrency.length; i++) {

            softAssertions.assertThat(expectedListResponseCurrency[i])
                    .isEqualToIgnoringGivenFields(actualResponseCurrencyBody[i]
                            , "buy", "sale");


        }

        softAssertions.assertAll();

    }
}
