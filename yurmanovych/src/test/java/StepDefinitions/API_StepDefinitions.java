package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.cs.A;
import cucumber.api.java.en.Given;
import libs.TestData;

public class API_StepDefinitions {

    final String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();

    @Given("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(
            int qtyOfPosts,
            String username,
            String password) {

        if(DEFAULT.equals(username)) username = TestData.VALID_LOGIN;
        if(DEFAULT.equals(password)) password = TestData.VALID_PASSWORD;

        for (int i = 0; i < qtyOfPosts; i++) {
              apiHelper.createPost("Post from API " +1, username, password);
        }
    }
}
