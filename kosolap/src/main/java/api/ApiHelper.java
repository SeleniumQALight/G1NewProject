package api;


import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());
    public String getToken (String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", password);
    ResponseBody responseBody =
            given()
                .contentType(ContentType.JSON)
                .body(requestParams.toMap())
            .when()
                .post(EndPoints.LOGIN)
            .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody();
        return responseBody.asString().replace("\"", "");
    }
    public PostDTO[] getAllPostsByUser(String userName) {
        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON).log().all()
                .when()
                .get(POST_BY_USER, userName)
                .then()
                .statusCode(200).log().all()
                .extract()
                .response().as(PostDTO[].class);
        return responseBody;
    }

    public void deleteAllPostsTillPresent(String userName, String password) {
        PostDTO[] listOfPostsByUser = getAllPostsByUser(userName);
        for (int i = 0; i < listOfPostsByUser.length; i++) {
            deletePostById(userName, password, listOfPostsByUser[i].get_id());
            logger.info(String.format("Post with id %s and title %s was deleted",
                    listOfPostsByUser[i].get_id(),
                    listOfPostsByUser[i].getTitle()));

        }
    }

    private void deletePostById(String userName, String password, String id) {
        String token = getToken(userName, password);

        JSONObject  bodyRequest = new JSONObject();
        bodyRequest.put("token", token);

        given()
                .contentType(ContentType.JSON)
                .body(bodyRequest)
        .when()
                .delete(EndPoints.DELETE_POST, id)
        .then()
                .statusCode(200);
    }
}
