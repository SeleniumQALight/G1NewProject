package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.ProfilePage;


public class ProfilePage_StepDefinitions {
    private ProfilePage profilePage = new ProfilePage(DriverHelper.getWebDriver());


    @Then("^User is redirected to 'Profile' page$")
    public void userIsRedirectedToProfilePage() {
        profilePage.checkIsRedirectToProfilePage();
    }

    @And("^User sees (\\d+) post in 'Posts list' on 'Profile' page$")
    public void userSeesPostInPostsListOnProfilePage(int expectedNumberOfPosts) {
        profilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
