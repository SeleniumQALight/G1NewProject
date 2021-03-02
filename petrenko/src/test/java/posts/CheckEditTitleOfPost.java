package posts;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CheckEditTitleOfPost extends BaseTest {
    final String POST_TITLE = "Yura Title of post." + Util.getDateAndTimeFormated();
    String newPostTitle = "++++++++++++++" + Util.getDateAndTimeFormated();

    @Before
    public void createNewPost() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectOnCreatePostPage()
                .enterTitleInToTitle(POST_TITLE)
                .enterTextInToInputBody("bshzbzx")
                .clickValueInDropDownInCreatePost("Частное сообщение")
                .clickOnButtonSavePost()
                .checkIsRedirectOnSinglePostPage()
                .checkIsSuccessMessageDisplayed()
                .clickOnProfileButton()
                .checkIsRedirectOnMyProfilePage()
                .checkIsPostWasAdded(POST_TITLE);


    }


    @Test

    public void checkEditOfTitle() {
        homePage.clickOnMyProfileButton().
                checkIsRedirectOnMyProfilePage().
                clickOnPostWithTitle(POST_TITLE).
                checkIsRedirectOnSinglePostPage().
                clickOnEditPostButton().
            //    checkIsRedirectOnEditPostPage().
                editTitleOfPost(newPostTitle).
                checkIsPostSuccessfullyUpdated().
                clickOnMyProfileButton().
                checkIsRedirectOnMyProfilePage().
                checkIsPostWasAdded(newPostTitle);

    }


    @After
    public void deletePost() {

        homePage.openHomePage()
                .checkIsButtonSignOutVisible()
                .clickOnMyProfileButton()
                .checkIsRedirectOnMyProfilePage().deletePostWhilePresent(newPostTitle);
    }

}