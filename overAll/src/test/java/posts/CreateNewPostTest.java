package posts;

import org.junit.After;
import org.junit.Test;

import baseTest.BaseTest;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost(){
        loginPage.loginWithValidCred()
                 .checkIsButtonSignOutVisible()
                 .clickOnCreatePostButton()
           .checkIsRedirectedOnCreatePostPage()
                .enterTitleInToInputTile("Taras Title of Post")
                .enterTextInToInputBody("Post body")
                .clickOnButtonSaveNewPost()
           .checkIsRedirectToSinglePostPage()
                .checkIsSuccessMessageDisplayed()
        ;
    }

    @After
    public void deletePost(){

    }
}
