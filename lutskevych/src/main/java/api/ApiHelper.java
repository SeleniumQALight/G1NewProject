package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static api.EndPoints.DELETE_POST;
import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .addFilter(new AllureRestAssured())
            .build();

    public String getToken (String userName, String pass){
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", pass);


        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                .when()
                        .post(EndPoints.LOGIN)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();
        return responseBody.asString().replace("\"","");
    }

    public PostDTO[] getAllPostsByUser(String userName) {
        PostDTO[] responseBody = given()
                .spec(requestSpecification)
                .when()
                .get(POST_BY_USER, userName)
                .then()
                .statusCode(200).log().all()
                .extract()
                .response().as(PostDTO[].class);
        return responseBody;
    }

    public void deletePostTillPresent(String userName, String password) {
        String token = getToken(userName, password);

        JSONObject requestParams = new JSONObject();
        requestParams.put("token", token);

        PostDTO[] responseBody = getAllPostsByUser(userName);

        if (responseBody.length > 0){
            for (int i = 0; i <responseBody.length; i++) {
                String id = responseBody[i].get_id();
                Response deleteBody =
                        given()
                                .spec(requestSpecification)
                                .body(requestParams.toMap()).log().all()
                        .when()
                                .delete(DELETE_POST, id)
                        .then()
                                .statusCode(200).log().all()
                                .extract()
                                .response();
            }
        }
    }

    public void createPost(String title, String userName, String password) {
        String token = getToken(userName, password);

        JSONObject requestParams = new JSONObject();
        requestParams.put("title", title);
        requestParams.put("body", "post body");
        requestParams.put("select1", "One Person");
        requestParams.put("token", token);

        given()
                .contentType(ContentType.JSON)
                .body(requestParams.toMap()).log().all()
                .when()
                .post(EndPoints.CREATE_POST)
                .then()
                .statusCode(200);
    }
}
