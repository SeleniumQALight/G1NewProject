package apiTests;

import api.APIHelper;
import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreaneNewPostByApiTest {
    final String userName = "tonya";
    final String password = "123456qwerty";
    APIHelper apiHelper = new APIHelper();

    @Test
    public void createNewPosts(){
        String token = apiHelper.getToken(userName, password);
        JSONObject requestParam = new JSONObject();
        requestParam.put("title", "New Post from API");
        requestParam.put("body", "New Post from API");
        requestParam.put("select1", "One Person");
        requestParam.put("token", token);

        given()
                .contentType(ContentType.JSON)
                .body(requestParam.toMap()).log().all()
        .when()
                .post(EndPoints.CREATE_POST)
        .then()
                .statusCode(200);

        PostDTO[] responseBody = apiHelper.getAllPostsByUser(userName);
        Assert.assertEquals(1, responseBody.length);

        PostDTO[] expectedPostsDTO =
                {
                        new PostDTO(requestParam.get("title").toString(),
                                requestParam.get("body").toString(),
                                requestParam.get("select1").toString(),
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
