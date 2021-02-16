package posts;

import Pages.SinglePostPage;
import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost(){
        singlePostPage=
                loginPage.loginWithValidCred()
                 .checkIsButtonSignOutVisible()
                 .clickOnCreatePostButton()
                 .checkIsRedirectedOnCteatePostPage()
                 .enterTitleInToInputTitle("Chepurna Title of Post")
                 .enterTextInToInputBody("Post body")
                 .clickOnButtonSaveNewPost()
                 .chechIsRedirectToSinglePostPage()
                 .checkIsSuccessMessageDisplayed();
    }

    //HOMEWORK 02-13
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
