package post;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import pages.SinglePostPageWithNewPost;

public class CreateNewPostTest extends BaseTest {


    @Test
    public void createNewPost() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectedOnCreatePostPage()
                .enterTitleIntoInputTitle("Cherchenko Lena Title of Post")
                .enterTextIntoInputBody("Cherchenko Post Body")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToSinglePostPage()
                .clickOnDeleteButton();

    }

}