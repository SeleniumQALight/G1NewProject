package apiTest;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import javax.xml.ws.Endpoint;

import static io.restassured.RestAssured.given;

public class ApiTest {
    @Test
    public void getAllPostsByUsers(){
        given()
                .contentType(ContentType.JSON).log().all()
       .when()
                .get(EndPoints.POST_BY_USER,"auto")
        .then()
                .statusCode(200).log().all();
    }
}
