package apiTests;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.MapAssert;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import api.AutorDTO;
import api.PostDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiTests {
    @Test
    public void getAllPostsByUser(){

        String userName = "autoapi";

        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON).log().all()
        .when()
                .get(POST_BY_USER, userName)
        .then()
                .statusCode(200).log().all()
                .extract()
                .response().as(PostDTO[].class);


        PostDTO[] expectedListPostDTO =
                {
                        new PostDTO("test2","test body2","All Users", new AutorDTO(userName), false),
                        new PostDTO("test","test body","All Users", new AutorDTO(userName), false)

                };

        System.out.println(expectedListPostDTO[0]);

        Assert.assertEquals(responseBody.length, expectedListPostDTO.length);
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedListPostDTO.length; i++) {
            softAssertions.assertThat(expectedListPostDTO[i].getAuthor()).isEqualToIgnoringGivenFields(responseBody[i].getAuthor(),"avatar");
            softAssertions.assertThat(expectedListPostDTO[i]).isEqualToIgnoringGivenFields(responseBody[i], "createdDate","_id", "author");
        }

        softAssertions.assertAll();


    }

    @Test
    public void getAllPostsByUserNegativ() {

        String userName = "autoapi";

        String responseBody = given()
                .contentType(ContentType.JSON).log().all()
                .when()
                .get(POST_BY_USER, userName+3333)
                .then()
                .statusCode(200).log().all()
                .extract().response().getBody().asString();
        Assert.assertEquals("","\"Sorry, invalid user requested.undefined\"", responseBody);
    }

    @Test
    public void getAllPostsByUserValidatePath() {

        String userName = "autoapi";

        Response response=  given()
                .contentType(ContentType.JSON).log().all()
                .when()
                .get(POST_BY_USER, userName)
                .then()
                .statusCode(200).log().all()
                .extract().response();
        List<String> titleList = response.jsonPath().getList("title", String.class);
        List<Map> authorList = response.jsonPath().getList("author", Map.class);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < titleList.size(); i++) {
            softAssertions.assertThat(titleList.get(i)).contains("test").as("Item number " + i);
            softAssertions.assertThat(authorList.get(i).get("username")).as("Item number " + i).isEqualTo(userName+1);
        }
        softAssertions.assertAll();
    }


}
