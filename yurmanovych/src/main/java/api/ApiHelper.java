package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    private final Logger LOG = Logger.getLogger(getClass());

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public String getToken(String username, String password){
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", username);
        requestParams.put("password", password);

        ResponseBody responseBody =
                given()
                    .spec(requestSpec)
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
                .spec(requestSpec)
                .when()
                .get(Endpoints.TEST_APP_POST_BY_USER, userName)
                .then()
                .statusCode(200).log().all()
                .extract()
                .response().as(PostDTO[].class);
        return responseBody;
    }

    public void deletePostsByUser(String username, String password) {
        PostDTO[] postsList = getAllPostsByUser(username);

        for (int i = 0; i < postsList.length; i++) {
            String id = postsList[i].get_id();
            deletePostById(username,password,id);
            LOG.info(String.format("Post with id %s was deleted", id));
        }

        Assert.assertEquals(0, getAllPostsByUser(username).length);
    }

    private void deletePostById(String username, String password, String id) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("token", getToken(username,password));

        given()
                .spec(requestSpec)
                .body(requestBody.toMap())
        .when()
                .delete(Endpoints.DELETE_POST, id)
        .then()
                .statusCode(200);
    }
}
