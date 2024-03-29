package StepDefinitions;


import api.ApiHelper;
import cucumber.api.java.en.Given;
import libs.TestData;

public class API_SteDefinitions {
    final String DEFAULT = "default";
    ApiHelper apiHelper = new ApiHelper();

    @Given("^Create (\\d+) new Posts  via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String userName, String passWord) {
        if (DEFAULT.equals(userName)) {
            userName = TestData.VALID_LOGIN;
        }

        if (DEFAULT.equals(passWord)) {
            passWord = TestData.VALID_PASSWORD;
        }
        for (int i = 0; i < numberOfPosts; i++) {

            apiHelper.createPost("Post from API " + i, userName, passWord);


        }


    }
}
