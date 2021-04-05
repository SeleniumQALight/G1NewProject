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

import static io.restassured.RestAssured.given;


public class ApiTestGetCurrency {


    @Test

    public void getCurrency() {

        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.CURRENCY, 5)
                .then()
                .statusCode(200).log().all()
                .extract().response().as(CurrencyDTO[].class);
        Assert.assertEquals("Response body length is not correct -" + responseBody.length, 4, responseBody.length);

        for (CurrencyDTO curr : responseBody) {

            System.out.println(String.format("Курс %s к %s покупки %s и продажи %s"
                    , curr.getCcy()
                    ,curr.getBase_ccy()
                    , curr.getBuy()
                    , curr.getSale()));

        }


    }


}
