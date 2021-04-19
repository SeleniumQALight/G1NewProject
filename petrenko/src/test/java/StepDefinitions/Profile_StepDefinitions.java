package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.MyProfilePage;

import javax.swing.plaf.PanelUI;

public class Profile_StepDefinitions {
private MyProfilePage profilePage = new MyProfilePage(DriverHelper.getWebDriver());

    @Then("^User is redirect to 'Profile' page$")
    public void userIsRedirectToProfilePage() {
        profilePage.checkIsRedirectOnMyProfilePage();
    }

    @And("^User sees (\\d+) posts in 'Posts lists' on 'Profile' page$")
    public void userSeesPostsInPostsListsOnProfilePage(int expectedNumberOfPosts) {
profilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
