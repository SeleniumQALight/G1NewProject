package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import libs.DriverManager;
import pages.ProfilePage;

public class ProfilePage_StepDefinitions {
    private ProfilePage profilePage = new ProfilePage((DriverManager.getWebDriver()));
    @Then("^User is redirected to 'Profile' page$")
    public void userIsRedirectedToProfilePage() {
        profilePage.checkIsRedirectToProfilePage();
    }

    @And("^User sees (\\d+) posts in 'Posts list' on 'Profile' page$")
    public void userSeesPostsInPostsListOnProfilePage(int expectedQtyOfPosts) {
        profilePage.verifyQtyOfPosts(expectedQtyOfPosts);
    }
}
