package apiTests;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import api.AuthorDTO;
import api.PostDTO;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.ContentType;

import java.util.List;
import java.util.Map;


public class ApiTests {

     final String USER_NAME = "autoapi";
     Logger logger = Logger.getLogger(getClass());
    @Test
    public void getAllPostsByUser(){



        PostDTO[] responseBody  = given()
                .contentType(ContentType.JSON).log().all()
        .when()
                .get(POST_BY_USER, USER_NAME)
        .then()
                .statusCode(200).log().all()
        .extract()
        .response().as(PostDTO[].class);

        PostDTO[] expectedListPostDTO = {
                new PostDTO("test2", "test body2", "All Users", new AuthorDTO(USER_NAME), false),
                new PostDTO("test", "test body", "All Users", new AuthorDTO(USER_NAME), false),
        };
        logger.info(expectedListPostDTO);
        Assert.assertEquals(responseBody.length, expectedListPostDTO.length);
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedListPostDTO.length; i++) {
            softAssertions.assertThat(expectedListPostDTO[i])
                    .isEqualToIgnoringGivenFields(responseBody[i], "_id", "createdDate", "author");
            softAssertions.assertThat(expectedListPostDTO[i].getAuthor())
                    .isEqualToIgnoringGivenFields(responseBody[i].getAuthor(), "avatar");
        }

        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUserNegative() {
        String responseBody =
                given()
                        .contentType(ContentType.JSON).log().all()
                        .when()
                        .get(POST_BY_USER, "notValidUser")
                        .then()
                        .statusCode(200).log().all()
                        .extract().response().getBody().asString();

        Assert.assertEquals("", "\"Sorry, invalid user requested.undefined\"", responseBody);
    }
        @Test
        public void getAllPostsByUserPath() {
            Response responseBody =
                    given()
                            .contentType(ContentType.JSON).log().all()
                            .when()
                            .get(POST_BY_USER, USER_NAME)
                            .then()
                            .statusCode(200).log().all()
                            .extract().response();
            List<String> titleList = responseBody.jsonPath().getList("title", String.class);
            List<Map> authorList = responseBody.jsonPath().getList("author", Map.class);
            SoftAssertions softAssertions = new SoftAssertions();
            for (int i = 0; i < titleList.size(); i++) {
                softAssertions.assertThat(titleList.get(i)).as("Item number " + i).contains("test");
                softAssertions.assertThat(authorList.get(i).get("username")).as("Item number " + i).isEqualTo(USER_NAME);
            }

            softAssertions.assertAll();
        }

        @Test
    public void getAllPostsByUserSchema() {

                given().
                        contentType(ContentType.JSON).log().all()
                .when()
                .get(POST_BY_USER, USER_NAME)
                .then().assertThat().body(matchesJsonSchemaInClasspath("respons.json"));
        }

}
