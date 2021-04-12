package StepDefinitions;

import Pages.ProfilePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import libs.DriverHelper;

public class ProfilePage_StepsDefinitions {
    private ProfilePage profilePage = new ProfilePage(DriverHelper.getWebDriver());

    @Then("^User is redirect to 'Profile' page$")
    public void userIsRedirectToProfilePage() {
        profilePage.chechIsRedirectToProfilePage();
    }

    @And("^User sees (\\d+) posts in 'Posts list' on 'Profile' page$")
    public void userSeesPostsInPostsListOnProfilePage(int expectedNumberOfPosts) {
        profilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
