package api;

import static api.EndPoints.DELETE_POST;
import static api.EndPoints.LOGIN;
import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;

import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import libs.TestData;

public class ApiHelper {

    public String getTokenForUser(String userName, String passWord){
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", passWord);

        ResponseBody response =
                given()
                        .contentType(ContentType.JSON)
                        .body(requestParams.toMap())
                        .when()
                        .post(LOGIN)
                        .then()
                        .statusCode(200)
//                        .log().all()
                        .extract()
                        .response().getBody();
        return response.asString();
    }

    public PostDTO[] getAllPostByUser(String userName) {
        PostDTO[] responseBody =
                given()
                        .contentType(ContentType.JSON).log().all()
                        .when()
                        .get(POST_BY_USER,  userName)
                        .then()
                        .statusCode(200).log().all()
                        .extract()
                        .response().getBody().as(PostDTO[].class);


        for (int i = 0; i < responseBody.length; i++) {
            System.out.println(responseBody[i].get_id() + " -> " + responseBody[i].getTitle());
        }

        return responseBody;

    }

    public void deletePostById(String userName, String pass, String postId) {
        String token = getTokenForUser(userName, pass);
        JSONObject requestParams = new JSONObject();
        requestParams.put("token", token.replace("\"",""));

        given()
                .contentType(ContentType.JSON)
                .body(requestParams.toMap())
                .when()
                .delete(DELETE_POST, postId)
                .then()
                .statusCode(200);
        System.out.println("Post with id " + postId + " was deleted via API");
    }

    public void deleteAllPostsByUser(String userName, String passWord){
        PostDTO[] listOfPostsByUser = getAllPostByUser(userName);

        for (int i = 0; i < listOfPostsByUser.length; i++) {
            deletePostById(userName,passWord, listOfPostsByUser[i].get_id());
            System.out.println(String.format("Post with id '%s' and '%s' was removed"
                    , listOfPostsByUser[i].get_id()
                    , listOfPostsByUser[i].getTitle()));
        }
    }
}
