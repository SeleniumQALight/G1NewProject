package posts;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;


public class CreateNewPostTest extends BaseTest {

    final String POST_TITLE = "Yura Title of post." + Util.getDateAndTimeFormated();

    @Test
    public void createNewPost() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectOnCreatePostPage()
                .enterTitleInToTitle(POST_TITLE)
                .enterTextInToInputBody("bshzbzx")
                .clickValueOptionInDropDownMenu("Частное сообщение")
                .clickOnButtonSavePost()
                .checkIsRedirectOnSinglePostPage()
                .checkIsSuccessMessageDisplayed();


    }


    @After
    public void deletePost() {

        homePage.openHomePage()
                .checkIsButtonSignOutVisible()
                .clickOnMyProfileButton()
                .checkIsRedirectOnMyProfilePage().deletePostWhilePresent(POST_TITLE);
//                .checkIsPostWithTheValidTitleIsVisible()
//                .clickOnPostWithTheValidTitle()
//                .checkIsRedirectOnSinglePostPage()
//                .clickOnDeletePostButton()
//                .clickOnMyProfileButton()
//                .checkIsPostWithTheValidTitleIsNotVisible(POST_TITLE);

    }


}
