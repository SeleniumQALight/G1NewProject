package apiTests;

import api.ApiHelper;
import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreateNewPostByApiTest {

    final String userName = "bald2004";
    final String passWord = "tetyyytetyyytetyyy";
    ApiHelper apiHelper = new ApiHelper();


@Before
public void deleteALLPosts() {
apiHelper.deleteAllPostsTillPresent(userName, passWord);


}



    @Test
    public void createNewPosts() {

        String token = apiHelper.getToken(userName, passWord);
        JSONObject requestParams  = new JSONObject();
        requestParams.put("title", "New POST from API");
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

        PostDTO[] expectedPostDto =
                {
                        new PostDTO(requestParams.get("title").toString()
                                ,requestParams.get("body").toString()
                                ,requestParams.get("select1").toString(),
                        new AuthorDTO(userName), false)

                };


        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedPostDto.length; i++) {
            softAssertions.assertThat(expectedPostDto[i])
                    .isEqualToIgnoringGivenFields(responseBody[i], "_id", "createdDate", "author");
            softAssertions.assertThat(expectedPostDto[i].getAuthor())
                    .isEqualToIgnoringGivenFields(responseBody[i].getAuthor(), "avatar");
        }


        softAssertions.assertAll();
    }

}
