package apiTests;

import api.AuthorDTO;
import api.CurrencyDTO;
import api.EndPoints;
import api.PostDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static api.EndPoints.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTests {

    final String USER_NAME="autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser(){
        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON).log().all()
//                .filter(new AllureRestAssured())
        .when()
                .get(POST_BY_USER, USER_NAME)
        .then()
                .statusCode(200).log().all()
                .extract()
                .response().as(PostDTO[].class);

        PostDTO[] expectedListPostDTO = {
            new PostDTO("test2", "test body2", "All Users"
                    , new AuthorDTO(USER_NAME), false),
            new PostDTO("test", "test body", "All Users"
                    , new AuthorDTO(USER_NAME), false)
        };
        logger.info(expectedListPostDTO[0].toString());
        Assert.assertEquals(responseBody.length, expectedListPostDTO.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i=0;i< expectedListPostDTO.length; i++){
            softAssertions.assertThat(expectedListPostDTO[i])
            .isEqualToIgnoringGivenFields(responseBody[i], "_id", "createdDate", "author");
            softAssertions.assertThat(expectedListPostDTO[i].getAuthor())
            .isEqualToIgnoringGivenFields(responseBody[i].getAuthor(), "avatar");
        }

        softAssertions.assertAll();

    }

    @Test
    public void getAllPostsByUserNegative(){

        String responseBody =
                given()
                    .contentType(ContentType.JSON).log().all()
                .when()
                    .get(POST_BY_USER,"notValidUser")
                .then()
                    .statusCode(200).log().all()
                    .extract().response().getBody().asString();

        Assert.assertEquals("","\"Sorry, invalid user requested.undefined\"", responseBody);
    }


    @Test
    public void getAllPostsByUserPath() {

        Response responseBody =
                given()
                        .contentType(ContentType.JSON).log().all()
                        .when()
                        .get(POST_BY_USER, USER_NAME)
                        .then()
                        .statusCode(200).log().all()
                        .extract().response();

        List<String> titleList = responseBody.jsonPath().getList("title", String.class);
        List<Map> authorList = responseBody.jsonPath().getList("author", Map.class);

        SoftAssertions softAssertions = new SoftAssertions();

        for(int i=0; i<titleList.size(); i++){

            softAssertions.assertThat(titleList.get(i)).contains("Item number" + i).as("test" + i);
            softAssertions.assertThat(authorList.get(i).get("username")).as("Item number" + i)
            .isEqualTo(USER_NAME);
        }


        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUserSchema() {


                given()
                        .contentType(ContentType.JSON).log().all()
                        .when()
                        .get(POST_BY_USER, USER_NAME)
                        .then().assertThat().body(matchesJsonSchemaInClasspath("respons.json"));


    }

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
            logger.info("Rate "+ expectedListResponseCurrency[i].getCcy() +
            " to " + expectedListResponseCurrency[i].getBase_ccy() + ". " +
            " Buy : " + expectedListResponseCurrency[i].getBuy() +
            ", Sell : " + expectedListResponseCurrency[i].getSale());
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
