package apiTests;

import api.ApiHelper;
import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;

public class CreateNewPostByApiTest {

    final String userName = "bald2004";
    final String password = "tetyyytetyyytetyyy";
    ApiHelper apiHelper = new ApiHelper();


@Before
public void deletePostWhilePresent() {

    Logger logger = Logger.getLogger(getClass());
    String token = apiHelper.getToken(userName, password);

    JSONObject requestParams  = new JSONObject();
    requestParams.put("token", token);

    Response responseBody =
            given()
                    .contentType(ContentType.JSON).log().all()
                    .when()
                    .get(POST_BY_USER, userName)
                    .then()
                    .statusCode(200).log().all()
                    .extract().response();


    List<String> idList = responseBody.jsonPath().getList("_id", String.class);


    logger.info("Count of Post is - " + idList.size());

    for (int i = 0; i < idList.size(); i++) {

        given()
                .contentType(ContentType.JSON)
                .body(requestParams.toMap()).log().all()
                .when()
                .delete(EndPoints.DELETE_POST, idList.get(i))
                .then()
                .statusCode(200);
        logger.info("Count of Post is - " + idList.size());
    }



    responseBody =
            given()
                    .contentType(ContentType.JSON).log().all()
                    .when()
                    .get(POST_BY_USER, userName)
                    .then()
                    .statusCode(200).log().all()
                    .extract().response();

    idList = responseBody.jsonPath().getList("_id", String.class);
    Assert.assertEquals("posts is not yet deleted.",0, idList.size());
    logger.info("All posts deleted.");
}



    @Test
    public void createNewPosts() {

        String token = apiHelper.getToken(userName, password);
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
