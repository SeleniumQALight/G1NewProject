package api;


import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;


public class ApiHelper {




    public String getToken(String username, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", username);
        requestParams.put("password", password);
        ResponseBody responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .body(requestParams.toMap())
                        .when().post(EndPoints.LOGIN)
                        .then().statusCode(200)
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


                .extract().response().as(PostDTO[].class);


        return  responseBody;
    }

    public void deletePostWhilePresent(String userName, String password) {
        Logger logger = Logger.getLogger(getClass());
        String token = getToken(userName, password);
        JSONObject requestParams = new JSONObject();
        requestParams.put("token", token);
        PostDTO[] postDTO = getAllPostsByUser(userName);

        for (int i = 0; i < postDTO.length; i++) {

            given()
                    .contentType(ContentType.JSON)
                    .body(requestParams.toMap()).log().all()
                    .when()
                    .delete(EndPoints.DELETE_POST, postDTO[i].get_id())
                    .then()
                    .statusCode(200);
        }

        postDTO = getAllPostsByUser(userName);

        Assert.assertEquals("posts is not yet deleted.", 0, postDTO.length);
        logger.info("All posts deleted.");
    }


}
