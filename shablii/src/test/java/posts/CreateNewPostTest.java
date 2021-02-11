package posts;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import pages.HomePage;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost(){
        loginPage.loginWithValidCred()
                 .checkIsButtonSignOutVisible()
                 .clickOnCreatePostButton()
        .checkIsRedirectedOnCreatePostPage()
            .enterTitleIntoInputTitle("Title of Post")
            .enterTextIntoInputBody("Post body")
            .clickOnButtonSaveNewPost()
        .checkIsRedirectToSinglePostPage()
            .checkIsSuccessMessageDisplayed()

        ;


    }

    @After
    public void deletePost(){

    }

}
