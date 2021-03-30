package apiTests;

import api.ApiHelper;
import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static api.EndPoints.DELETE_POST;
import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;

public class CreateNewPostByApiTest {
    final String userName = "testiryna";
    final String password = "123456qwerty";
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void postDeletion(){
        String token = apiHelper.getToken(userName, password);

        JSONObject requestParams = new JSONObject();
        requestParams.put("token", token);

        PostDTO[] responseBody = apiHelper.getAllPostsByUser(userName);


        if (responseBody.length > 0){
            for (int i = 0; i <responseBody.length; i++) {
                String id = responseBody[i].get_id();
                Response deleteBody =
                        given()
                                .contentType(ContentType.JSON).log().all()
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

    @Test
    public void createNewPosts() {
        String token = apiHelper.getToken(userName, password);

        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "New Post from API222222");
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

        PostDTO[] responseBody = apiHelper.getAllPostsByUser(userName);
        Assert.assertEquals(1, responseBody.length);

        PostDTO[] expectedPostDTO =
                {
                        new PostDTO(requestParams.get("title").toString(),
                                requestParams.get("body").toString(),
                                requestParams.get("select1").toString(),
                                new AuthorDTO(userName),
                                false)
                };
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedPostDTO.length; i++) {
            softAssertions.assertThat(expectedPostDTO[i])
                    .isEqualToIgnoringGivenFields(responseBody[i], "_id", "createdDate", "author");
            softAssertions.assertThat(expectedPostDTO[i].getAuthor())
                    .isEqualToIgnoringGivenFields(responseBody[i].getAuthor(), "avatar");
        }

        softAssertions.assertAll();






    }
}
