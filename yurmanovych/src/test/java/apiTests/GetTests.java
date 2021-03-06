package apiTests;

import api.AuthorDTO;
import api.Endpoints;
import api.PostDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static api.Endpoints.TEST_APP_POST_BY_USER;
import static io.restassured.RestAssured.given;
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class GetTests {
    final private Logger LOG = Logger.getLogger(getClass());
    final private String USER_NAME = "autoapi";

    @Test
    public void getPBBranches() {
        String city = "Днепропетровск";
        String branch = "Титова";

        Response response = given()
                .contentType(ContentType.JSON).log().uri()
                .when()
                .get(Endpoints.PB_GET_BRANCHES, city, branch)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response();

        String citiesAR = response.jsonPath().getString("city");
        String branchAR = response.jsonPath().getString("branch");

        Assert.assertEquals(city, response.jsonPath().getString("city"));
    }

    @Test
    public void getPostsByUser() {
        PostDTO[] AR = given()
                .contentType(ContentType.JSON).log().uri()
                .filter(new AllureRestAssured())
                .when()
                .get(TEST_APP_POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200).log().all()
                .extract()
                .as(PostDTO[].class);

        PostDTO[] ER = {
                new PostDTO(
                        "test2",
                        "test body2",
                        "All Users",
                        new AuthorDTO(USER_NAME),
                        false),
                new PostDTO(
                        "test",
                        "test body",
                        "All Users",
                        new AuthorDTO(USER_NAME),
                        false),
        };
        Assert.assertEquals(AR.length, ER.length);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < ER.length; i++) {
            LOG.info("Expected list: " + ER[i].toString());
            LOG.info("Actual list: " + AR[i].toString());
            softAssertions.assertThat(ER[i])
                    .isEqualToIgnoringGivenFields(
                            AR[i], "_id", "createdDate", "author");
            softAssertions.assertThat(ER[i].getAuthor())
                    .isEqualToIgnoringGivenFields(
                            AR[i].getAuthor(), "avatar");
        }

        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUsersNeg() {
        String response = given()
                .contentType(ContentType.JSON).log().all()
                .filter(new AllureRestAssured())
                .when()
                .get(TEST_APP_POST_BY_USER, "invalid user")
                .then()
                .statusCode(200).log().all()
                .extract().response().asString();

        Assert.assertEquals("\"Sorry, invalid user requested.undefined\"", response);
    }

    @Test
    public void getAllPostsByUsersPath() {
        Response response = given()
                .contentType(ContentType.JSON).log().all()
                .filter(new AllureRestAssured())
                .when()
                .get(TEST_APP_POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200).log().all()
                .extract().response();

        List<String> titleList = response.jsonPath().getList("title", String.class);
        List<Map> authorList = response.jsonPath().getList("author", Map.class);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < titleList.size(); i++) {
            softAssertions.assertThat(titleList.get(i)).contains("test").as("Item number " + i);
            softAssertions.assertThat(authorList.get(i).get("username")).as("Item number " + i).isEqualTo(USER_NAME);
            softAssertions.assertAll();
        }
    }

//    @Test
//    public void getAllPostsByUsersJsonSchema(){
//        given()
//                .contentType(ContentType.JSON).log().all()
//                .when()
//                .get(TEST_APP_POST_BY_USER, USER_NAME)
//                .then()
//                .assertThat()
//                .body(matchesJsonSchemaInClasspath("resources/response.json"));
//    }




}
