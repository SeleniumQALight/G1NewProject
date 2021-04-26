package apiTests;
import org.junit.Test;
import org.apache.log4j.Logger;
import api.CurrencyDTO;
import io.restassured.http.ContentType;

import static api.EndPoints.GET_CURRENCY_RATE;
import static io.restassured.RestAssured.given;

public class PrivatBankApiTest {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getPrivatBankCurApi(){

        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON).log().all()
                .when()
                .get(GET_CURRENCY_RATE)
                .then()
                .statusCode(200).log().all()
                .extract()
                .response().as(CurrencyDTO[].class);


        for (int i = 0; i < responseBody.length; i++) {
            logger.info("Exchange rate " + responseBody[i].getCcy() + " for " + responseBody[i].getBase_ccy() + " - buy " + responseBody[i].getBuy() +
                    " , sell " +responseBody[i].getSale());
        }

    }
}
