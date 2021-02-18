package posts;

import org.junit.After;
import org.junit.Test;

import baseTest.BaseTest;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "Vera Title of Post";
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
