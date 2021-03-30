package api;

import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    public String getToken(String username, String password){
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", username);
        requestParams.put("password", password);

        ResponseBody responseBody =
                given()
                    .contentType(ContentType.JSON)
                    .body(requestParams.toMap())
                .when()
                    .post(Endpoints.LOGIN)
                .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response().getBody();
        return responseBody.asString().replace("\"","");
    }

    public PostDTO[] getAllPostsByUser(String userName) {
        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON).log().all()
                .when()
                .get(Endpoints.TEST_APP_POST_BY_USER, userName)
                .then()
                .statusCode(200).log().all()
                .extract()
                .response().as(PostDTO[].class);
        return responseBody;
    }
}