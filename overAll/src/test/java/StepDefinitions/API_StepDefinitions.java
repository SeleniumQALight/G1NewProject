package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.And;
import libs.TestData;

public class API_StepDefinitions {
    ApiHelper apiHelper = new ApiHelper();

    @And("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' passWord$")
    public void createNewPostsViaAPIForDefaultUser(int numberOfPosts, String user, String passWord) {
        if ("default".equals(user)){
            user = TestData.VALID_LOGIN;
        }
        if ("default".equals(passWord)){
            passWord = TestData.VALID_PASSWORD;
        }
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createNewPosts("Post From Api " + i, user, passWord);
        }

    }
}
