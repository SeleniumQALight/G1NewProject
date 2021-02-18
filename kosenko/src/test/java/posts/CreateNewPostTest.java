package posts;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {


    @Test
    public void createNewPost() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectedOnCreatePostPage()
                .enterTitleIntoInputTitle("Kosenko Title of Post")
                .enterTextIntoInputBody("Kosenko Post Body")
                .clickOnButtonSaveNewPost()
                .chechIsRedirectToSinglePostPage()
                .checkIsSuccessMessageDisplayed();
    }
    @After

    public void deletePost(){
        singlePostPage.clickOnMyProfileButton()
                .chechIsRedirectToProfilePage()
                .clickOnPostTitle()
                .chechIsRedirectToSinglePostPage()
                .clickOnDeletePostButton()
                .checkIsSuccessDeletedMessageDisplayed();
    }
}