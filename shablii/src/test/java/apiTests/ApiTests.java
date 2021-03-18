package apiTests;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    @Test
    public void getAllPostsByUser(){
        given()
                .contentType(ContentType.JSON).log().all()
                .when()
                    .get(EndPoints.POST_BY_USER, "auto")
                .then()
                    .statusCode(200).log().all();
    }
}
