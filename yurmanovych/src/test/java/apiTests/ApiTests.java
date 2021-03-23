package apiTests;

import api.Endpoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {

    @Test
    public void getPBExchangeRates(){
        given()
                .contentType(ContentType.JSON).log().uri()
        .when()
                .get(Endpoints.PB_GET_EXCH_RATES)
        .then()
                .statusCode(200).log().all();
    }

    @Test
    public void getPostsByUser(){
        given()
                .contentType(ContentType.JSON).log().uri()
        .when()
                .get(Endpoints.TEST_APP_POST_BY_USER, "autoapi")
        .then()
                .statusCode(200).log().all();
    }
}
