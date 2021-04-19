package StepDefinitions;

import api.ApiHelper;
import api.ApiPrivatHelper;
import cucumber.api.java.en.Given;
import libs.TestData;

public class API_StepDefinition {
    final String DEFAULTE = "defaulte";
    ApiHelper apiHelper = new ApiHelper();
    ApiPrivatHelper apiPrivatHelper = new ApiPrivatHelper();

    @Given("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String userName, String passWord) {
        if (DEFAULTE.equals(userName)){
            userName = TestData.VALID_LOGIN;
        }
        if (DEFAULTE.equals(passWord)) {
            passWord = TestData.VALID_PASSWORD;
            for (int i = 0; i < numberOfPosts ; i++) {
                apiHelper.createPost("Post from API " + i, userName, passWord );
            }
        }
    }

    @Given("^User gets buy and sale prices by '(.*)' currency name using API request$")
    public void userGetsBuyAndSalePricesByCurrencyNameCurrencyNameUsingAPIRequest(String currencyName) {
        apiPrivatHelper.getBuySaleValueApi(currencyName);
    }

}
