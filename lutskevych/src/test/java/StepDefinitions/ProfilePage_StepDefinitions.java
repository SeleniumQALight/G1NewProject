package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.MyProfilePage;

public class ProfilePage_StepDefinitions {

    private MyProfilePage myProfilePage = new MyProfilePage(DriverHelper.getWebDriver());

    @Then("^User is redirect to 'Profile' page$")
    public void userIsRedirectToProfilePage() {
        myProfilePage.checkIsSuccessRedirectToMyProfilePage();
    }

    @And("^User sees (\\d+) post in 'Posts list' on 'Profile' page$")
    public void userSeesPostInPostsListOnProfilePage(int expectedNumberOfPosts) {
        myProfilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
