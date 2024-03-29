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

    public String getToken(String userName, String passWord){
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", passWord);

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

        return responseBody.asString().replace("\"", "");
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

    public void deletePostsTillPresent(String userName, String password) {
        PostDTO[] lisOfPostsByUser = getAllPostsByUser(userName);
        for (int i = 0; i < lisOfPostsByUser.length; i++) {
            deletePostById(userName, password, lisOfPostsByUser[i].get_id());
            logger.info(String.format("Post with id %s and title %s was deleted",
                    lisOfPostsByUser[i].get_id() ,
                    lisOfPostsByUser[i].getTitle()
                    ));
        }
        Assert.assertEquals(0, getAllPostsByUser(userName).length);
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

    public void createPost(String title, String userName, String passWord) {
        String token = getToken(userName, passWord);

        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "New Post from API");
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