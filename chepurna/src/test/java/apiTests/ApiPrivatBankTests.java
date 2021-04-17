package apiTests;
import api.CurrencyDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Test;

import static api.EndPoints.GET_PRIVAT_CURRENCY;
import static io.restassured.RestAssured.given;

public class ApiPrivatBankTests {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getPrivatCurrencyAPI(){

//GET response
        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON).log().all()
                .when()
                .get(GET_PRIVAT_CURRENCY)
                .then()
                .statusCode(200).log().all()
                .extract()
                .response().as(CurrencyDTO[].class);

//Expected List of currency DTO
        CurrencyDTO[] expectedListCurremcyDTO = {
                //   new CurremcyDTO("USD", "UAH"   ),
                //   new CurremcyDTO("EUR", "UAH"   ),
                //   new CurremcyDTO("RUR", "UAH"   ),
                //  new CurremcyDTO("BTC", "USD"   )
        };

        for (int i = 0; i < responseBody.length; i++) {
            //softAssertions.assertThat(expectedListCurremcyDTO[i].equals(responseBody[i]));
            logger.info("Курс " + responseBody[i].getCcy() +
                    " к " + responseBody[i].getBase_ccy() +
                    " покупки " + responseBody[i].getBuy() +
                    " и продажи " +responseBody[i].getSale());
        }

    }
}

