package apiTests;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import api.AutorDTO;
import api.PostDTO;
import io.restassured.http.ContentType;

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
}
