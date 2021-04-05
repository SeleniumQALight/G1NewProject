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

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTestGetCurrency {


    @Test

    public void getCurrency() {
        Logger logger = Logger.getLogger(getClass());


        CurrencyDTO[] currency = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.CURRENCY)
                .then()
                .statusCode(200).log().all()
                .extract().response().as(CurrencyDTO[].class);
        Assert.assertEquals(4, currency.length);

        for (CurrencyDTO curr : currency) {

            System.out.println(String.format("Курс %s к UAH покупки %s и продажи %s"
                    , curr.getCcy()
                    , curr.getBuy()
                    , curr.getSale()));

        }


    }


}
