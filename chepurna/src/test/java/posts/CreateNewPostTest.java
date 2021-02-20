package posts;

import Pages.SinglePostPage;
import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "Chepurna Title of Post" + Util.getDateAndTimeFormated();
    @Test
    public void createNewPost(){
        singlePostPage=
                loginPage.loginWithValidCred()
                 .checkIsButtonSignOutVisible()
                 .clickOnCreatePostButton()
                 .checkIsRedirectedOnCteatePostPage()
                 .enterTitleInToInputTitle(POST_TITLE)
                 .enterTextInToInputBody("Post body")
                        .selectTextInDropDownRole("Частное сообщение")
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
