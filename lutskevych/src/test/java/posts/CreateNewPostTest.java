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
                .enterTitleToInputTitle("Iryna Lutskevych Title Post 222222")
                .enterTextInToInputBody("Post body **********************")
                .clickOnButtonSaveNewPost()
            .checkIsRedirectToSinglePostPage()
                .checkIsSuccessMessageDisplayed();

       }

       @After
    public void deletePost(){

       }

   }
