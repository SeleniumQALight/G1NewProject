package apiTest;

import api.NBUCurrencyDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class NBUCurrencyResponseTest {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void currencyResponseNBUSchemaValidationTest(){
        given()
                .contentType(ContentType.JSON).log().all()
                .filter(new AllureRestAssured())
         .when()
                .get("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5")
         .then()
                .statusCode(200).log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("responseNBU.json"));
    }

    @Test
    public void currencyResponseNBUSchemaTest(){
       NBUCurrencyDTO[] bodyResponse =
        given()
                .contentType(ContentType.JSON).log().all()
                .filter(new AllureRestAssured())
        .when()
                .get("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5")
        .then()
                .statusCode(200).log().all()
                .extract()
                .response().as(NBUCurrencyDTO[].class);
                //.assertThat().body(matchesJsonSchemaInClasspath("responseNBU.json.json"));
        NBUCurrencyDTO[] expectedListNBUCurrencyDTO = {
         new NBUCurrencyDTO("USD","UAH","27.70000","28.10000"),
         new NBUCurrencyDTO("EUR","UAH","32.50000","33.12000"),
         new NBUCurrencyDTO("RUR", "UAH","0.35700","0.38700"),
         new NBUCurrencyDTO("BTC", "USD","54722.4915","60482.7537")
        };


        //logger.info(expectedListNBUCurrencyDTO);
        Assert.assertEquals(bodyResponse.length,expectedListNBUCurrencyDTO.length);
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i <bodyResponse.length ; i++) {
            softAssertions.assertThat(expectedListNBUCurrencyDTO[i]).isEqualToIgnoringGivenFields(bodyResponse[i], "buy","sale");
            logger.info("Exchange   " + bodyResponse[i].getCcy()+ "  to  " + bodyResponse[i].getBase_ccy() + "  buy   "
                    + bodyResponse[i].getBuy()+ "  sale  " + bodyResponse[i].getSale() );

        }



        softAssertions.assertAll();
    }
}
