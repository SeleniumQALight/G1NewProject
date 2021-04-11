package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.MyProfilePage;

public class ProfilePage_StepDefinitions {

    private MyProfilePage myProfilePage = new MyProfilePage(DriverHelper.getWebDriver());



    @Then("^User is redirect to 'Profile' page$")
    public void userIsRedirectToProfilePage() {
        myProfilePage.checkIsRedirectedOnMyProfilePage();
    }

    @And("^User sees (\\d+) posts in 'Posts List' on 'Profile' page$")
    public void userSeesPostsInPostsListOnProfilePage(int expectedNumberOfPosts) {
        myProfilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
