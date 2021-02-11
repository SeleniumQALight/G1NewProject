package posts;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {

    @Test

    public void createNewPost(){
    loginPage.loginWithValidCred()
            .checkisButtonSignOutVisible()
            .clickOnCreatePostButton()
            .checkIsRedirectOnCreatePostPage()
            .enterTitleInToTitle("Yura Title of post")
            .enterTextInToInputBody("bshzbzx")
            .clickOnButtonSavePost()
            .checkIsRedirectOnSinglePostPage()
            .checkIsSuccessMessageDisplayed();

    }

    @After
    public void deletePost(){
      //  homePage.
    }

}
