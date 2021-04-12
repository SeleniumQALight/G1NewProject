package StepDefinitions;

import api.APIHelper;
import cucumber.api.java.en.Given;
import libs.TestData;

public class API_StepDefinition {
    final String DEFAULT = "default";
    private APIHelper apiHelper = new APIHelper();

    @Given("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword
            (int numberOfPosts, String userName, String passWord) {
        if (DEFAULT.equals(userName)){
            userName = TestData.VALID_LOGIN;
        }
        if (DEFAULT.equals(passWord)){
            passWord = TestData.VALID_PASSWORD;
        }
        for (int i = 0; i < numberOfPosts; i++) {
           apiHelper.createPost("Post from API" + i, userName, passWord);
        }


    }
}
