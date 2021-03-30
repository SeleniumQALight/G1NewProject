package apiTest;

import api.ApiHelper;
import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreateNewPostByApiTest {
    final String userName = "cherchenko";
    final String passWord = "123456qwerty";
    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void createNewPosts(){
        String token = apiHelper.getToken(userName,passWord);

        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "New Post from API LC");
        requestParams.put("body","post Body to post API CL");
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
        Assert.assertEquals( 1, responseBody.length);

        PostDTO[] expectedPostsDTO =
                {
                        new PostDTO(requestParams.get("title").toString(),
                                requestParams.get("body").toString(),
                                requestParams.get("select1").toString(),
                                new AuthorDTO(userName),
                                false)
                };

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedPostsDTO.length; i++) {
            softAssertions.assertThat(expectedPostsDTO[i])
                    .isEqualToIgnoringGivenFields(responseBody[i], "_id", "createdDate", "author");
            softAssertions.assertThat(expectedPostsDTO[i].getAuthor())
                    .isEqualToIgnoringGivenFields(responseBody[i].getAuthor(), "avatar");
        }


        softAssertions.assertAll();

    }
}
