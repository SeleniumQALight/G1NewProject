package posts;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost(){
        loginPage.loginWithValidCred()
        .checkIsButtonSignOutVisible()
        .clickOnCreatePostButton()
        .checkIsRedirectedOnCreatePostPage()
                   .enterTitleInToInputTitle("Yuliia Title of Post")
                   .enterTextInToInputBody("Post body")
                   .clickOnButtonSaveNewPost()
                 .checkIsRedirectToSinglePostPage()


        ;

    }
    @After
    public void deletePost(){

    }
}
