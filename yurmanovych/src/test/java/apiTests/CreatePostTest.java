package apiTests;

import api.ApiHelper;
import api.AuthorDTO;
import api.Endpoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CreatePostTest {

    private final String username = "kate";
    private final String password = "aB123456123456";

    @Test
    public void createPost(){
        String token = new ApiHelper().getToken(username,password);

        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "New Post from API");
        requestParams.put("body", "post body");
        requestParams.put("select1", "One Person");
        requestParams.put("token", token);

        given()
                .contentType(ContentType.JSON)
                .body(requestParams.toMap()).log().all()
                .when()
                .post(Endpoints.CREATE_POST)
                .then()
                .statusCode(200);

        PostDTO[] responseBody = new ApiHelper().getAllPostsByUser(username);
        Assert.assertEquals( 1, responseBody.length);

        PostDTO[] expectedPostsDTO =
                {
                        new PostDTO(requestParams.get("title").toString(),
                                requestParams.get("body").toString(),
                                requestParams.get("select1").toString(),
                                new AuthorDTO(username),
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

