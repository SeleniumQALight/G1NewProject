package api;

import io.restassured.http.ContentType;
import libs.TestData;

import static api.EndPoints.GET_PRIVAT_CURRENCY;
import static io.restassured.RestAssured.given;

public class CurrencyAPI {
    public static void getCurrensyAPI(String currensy) {

//GET response
        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON).log().all()
                .when()
                .get(GET_PRIVAT_CURRENCY)
                .then()
                .statusCode(200).log().all()
                .extract()
                .response().as(CurrencyDTO[].class);
   if(currensy.equals("RUB")) {currensy = "RUR";}

        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody[i].getCcy().equals(currensy)) {
                TestData.rateBuyAPI = responseBody[i].getBuy().replaceAll("[0.]+$", "");
                TestData.rateSellAPI = responseBody[i].getSale().replaceAll("[0.]+$", "");
            }
        }




    }
}


