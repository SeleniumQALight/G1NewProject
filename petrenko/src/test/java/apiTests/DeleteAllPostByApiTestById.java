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

public class DeleteAllPostByApiTestById {

    final String userName = "bald2004";
    final String password = "tetyyytetyyytetyyy";
    ApiHelper apiHelper = new ApiHelper();


    @Test

    public void deletePostWhilePresent() {
      apiHelper.deletePostWhilePresent(userName,password);
    }

}