package apiTests;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;

import org.junit.Test;

import io.restassured.http.ContentType;

public class ApiTests {
    @Test
    public void getAllPostsByUser(){
        given()
                .contentType(ContentType.JSON).log().all()
        .when()
                .get(POST_BY_USER, "autoapi")
        .then()
                .statusCode(200).log().all();
    }
}
