package apiTests;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static api.EndPoints.*;
import static io.restassured.RestAssured.given;

public class ApiTests {

    @Test
    public void getAllPostsByUser(){
        given()
                .contentType(ContentType.JSON).log().all()
        .when()
                .get(POST_BY_USER, "auto")
        .then()
                .statusCode(200).log().all();

    }

    @Test
    public void getCurrenciesRateCashJson(){
        given()
                .contentType(ContentType.JSON).log().all()
        .when()
                .get(currencyExchangeCashJson)
        .then()
                .statusCode(200).log().all();
    }

    @Test
    public void getCurrenciesRateCashXML(){
        given()
                .contentType(ContentType.JSON).log().all()
        .when()
                .get(currencyExchangeCashXML)
        .then()
                .statusCode(200).log().all();
    }

    @Test
    public void getNepalTourJSON(){
        given()
                .contentType(ContentType.JSON).log().all()
        .when()
                .get(nepalURL)
        .then()
                .statusCode(200).log().all();

    }


}
