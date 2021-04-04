package StepDefinitions;

import cucumber.api.java.en.Then;
import libs.DriverManager;
import pages.ProfilePage;

public class ProfilePage_StepDefinitions {
    private ProfilePage profilePage = new ProfilePage(DriverManager.getWebDriver());

    @Then("^User is redirect to 'Profile' page$")
    public void userIsRedirectToProfileProfilePage() {
        profilePage.checkIsRedirectToProfilePage();
    }

    @Then("^User sees (\\d+) posts in 'Posts' list on 'Profile' page$")
    public void userSeesPostsInPostsListOnProfilePage(int expectedNumberOfPosts) {
        profilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
