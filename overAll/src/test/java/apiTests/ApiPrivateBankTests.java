package apiTests;
import api.CurremcyDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Test;

import static api.EndPoints.GET_PRIVAT_CURRENCY;
import static io.restassured.RestAssured.given;

public class ApiPrivateBankTests {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getPrivateCurrency(){

//GET response
        CurremcyDTO[] responseBody = given()
                .contentType(ContentType.JSON).log().all()
         .when()
                .get(GET_PRIVAT_CURRENCY)
         .then()
                .statusCode(200).log().all()
                .extract()
                .response().as(CurremcyDTO[].class);

//Expected List of currency DTO
        CurremcyDTO[] expectedListCurremcyDTO = {
             //   new CurremcyDTO("USD", "UAH"   ),
             //   new CurremcyDTO("EUR", "UAH"   ),
             //   new CurremcyDTO("RUR", "UAH"   ),
             //  new CurremcyDTO("BTC", "USD"   )
        };


//ASSERT lengths
       // Assert.assertEquals(responseBody.length, expectedListCurremcyDTO.length);

//SOFT ASSERTIONS
        //SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < responseBody.length; i++) {
            //softAssertions.assertThat(expectedListCurremcyDTO[i].equals(responseBody[i]));
            logger.info("Курс " + responseBody[i].getCcy() +
                         " к " + responseBody[i].getBase_ccy() +
                         " покупки " + responseBody[i].getBuy() +
                         " и продажи " +responseBody[i].getSale());
        }

       // softAssertions.assertAll();
    }
}
