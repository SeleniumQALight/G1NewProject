package apiTests;

import static api.EndPoints.CREATE_POST;
import static io.restassured.RestAssured.given;

import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import api.ApiHelper;
import api.AutorDTO;
import api.PostDTO;
import io.restassured.http.ContentType;

public class CreatePostByAPI {
    final String userName = "auto";
    final String passWord = "123456qwerty";
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deletePostsIfPresent(){
        apiHelper.deleteAllPostsByUser(userName,passWord);
    }

    @Test
    public void createNewPosts() {
        String token = apiHelper.getTokenForUser(userName, passWord);
        System.out.println(token);
        System.out.println("");

        JSONObject requestParams = new JSONObject();
        requestParams.put("token", token.replace("\"",""));
        requestParams.put("title", "New post by api Taras");
        requestParams.put("body", "post body");
        requestParams.put("select1", "One Person");

        given()
                .contentType(ContentType.JSON)
                .body(requestParams.toMap()).log().all()
                .when()
                .post(CREATE_POST)
                .then()
                .statusCode(200)
        ;
        PostDTO[] responseBody = apiHelper.getAllPostByUser(userName);
        Assert.assertEquals(responseBody.length , 1 );

        PostDTO[] expectedListPostDTO =
                {
                        new PostDTO(requestParams.get("title").toString()
                                ,requestParams.get("body").toString(),
                                requestParams.get("select1").toString(),
                                new AutorDTO(userName), false)
                };


        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedListPostDTO.length; i++) {
            softAssertions.assertThat(expectedListPostDTO[i].getAuthor()).isEqualToIgnoringGivenFields(responseBody[i].getAuthor(),"avatar");
            softAssertions.assertThat(expectedListPostDTO[i]).isEqualToIgnoringGivenFields(responseBody[i], "createdDate","_id", "author");
        }

        softAssertions.assertAll();

    }
}
