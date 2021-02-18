package posts;

import org.junit.After;
import org.junit.Test;

import baseTest.BaseTest;
import libs.Util;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "Taras Title of Post" + Util.getDateAndTimeFormated();

    @Test
    public void createNewPost(){
        loginPage.loginWithValidCred()
                 .checkIsButtonSignOutVisible()
                 .clickOnCreatePostButton()
           .checkIsRedirectedOnCreatePostPage()
                .enterTitleInToInputTile(POST_TITLE)
                .enterTextInToInputBody("Post body")
                .clickOnButtonSaveNewPost()
           .checkIsRedirectToSinglePostPage()
                .checkIsSuccessMessageDisplayed()
                .clickOnProfileButton()
            .checkIsRedirectToProfilePage()
                .checkPostWasAdded(POST_TITLE);
        ;
    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .checkIsButtonSignOutVisible()
                .clickOnProfileButton()
            .checkIsRedirectToProfilePage()
                .deletePostWhilePresent(POST_TITLE);
    }
}
