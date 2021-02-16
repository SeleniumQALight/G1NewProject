package posts;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import pages.LoginPage;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost(){
    loginPage.loginWithValidCred()
             .checkIsButtonSignOutVisible()
            .clickOnCreatePostButton()
            .checkIsRedirectedOnCreatePostPage()
            .enterTitleInToInputTitle(" Roman Title of Post")
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
