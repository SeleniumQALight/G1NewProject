package api;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;


public class ApiHelper {
Logger logger = Logger.getLogger(getClass());

RequestSpecification requestSpecification = new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .addFilter(new AllureRestAssured())
        .build();




    public String getToken(String username, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", username);
        requestParams.put("password", password);
        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when().post(EndPoints.LOGIN)
                        .then().statusCode(200)
                        .log().all()
                        .extract().response().getBody();
        return responseBody.asString().replace("\"", "");
    }

    public PostDTO[] getAllPostsByUser(String userName) {

        PostDTO[] responseBody = given()
                .spec(requestSpecification)

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
                    .spec(requestSpecification)
                    .body(requestParams.toMap())
                    .when()
                    .delete(EndPoints.DELETE_POST, postDTO[i].get_id())
                    .then()
                    .statusCode(200);
        }

        postDTO = getAllPostsByUser(userName);

        Assert.assertEquals("posts is not yet deleted.", 0, postDTO.length);
        logger.info("All posts deleted.");
    }


    public void deleteAllPostsTillPresent(String userName, String password) {
        PostDTO[] listOfPostsByUser = getAllPostsByUser(userName);

        for (int i = 0; i < listOfPostsByUser.length; i++) {

            deletePostById(userName, password, listOfPostsByUser[i].get_id());
                logger.info(String.format("Post with id %s and title %s was deleted"
                        , listOfPostsByUser[i].get_id()
                        , listOfPostsByUser[i].getTitle()));

        }

        Assert.assertEquals(0, getAllPostsByUser(userName).length );




    }

    private void deletePostById(String userName, String password, String id) {
        String token = getToken(userName, password);
        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("token", token);
        given()
                .spec(requestSpecification)
                .body(bodyRequest.toMap())
        .when()
                .delete(EndPoints.DELETE_POST, id)
        .then()
                .statusCode(200);


    }
}
