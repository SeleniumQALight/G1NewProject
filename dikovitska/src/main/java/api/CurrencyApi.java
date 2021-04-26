package api;
import io.restassured.http.ContentType;
import libs.TestData;
import static api.EndPoints.GET_CURRENCY_RATE;
import static io.restassured.RestAssured.given;

public class CurrencyApi {
    public static void getCurrencyApi(String currency) {

        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON).log().all()
                .when()
                .get(GET_CURRENCY_RATE)
                .then()
                .statusCode(200).log().all()
                .extract()
                .response().as(CurrencyDTO[].class);
        if(currency.equals("RUB")) {currency = "RUR";}

        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody[i].getCcy().equals(currency)) {
                TestData.rateBuyApi = responseBody[i].getBuy().replaceAll("[0.]+$", "");
                TestData.rateSellApi = responseBody[i].getSale().replaceAll("[0.]+$", "");
            }
        }

    }
}
