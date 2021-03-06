package posts;

import Pages.SinglePostPage;
import baseTest.BaseTest;
import categories.SmokeTests;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import suit.Smoke;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "Chepurna Title of Post" + Util.getDateAndTimeFormated();
    @Category(SmokeTests.class)
    @Test
    public void createNewPost() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectedOnCteatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextInToInputBody("Post body")
                //       .selectTextInDropDownRole("Частное сообщение")
                .selectTextInDropDownLikeManual("Частное сообщение")
                .clickOnButtonSaveNewPost()
                .chechIsRedirectToSinglePostPage()
                .checkIsSuccessMessageDisplayed()
                .clickOnMyProfileButton()
                .chechIsRedirectToProfilePage()
                .checkISPostWasAdded(POST_TITLE, 1);
    }

    @After
    public void deletePost(){
        homePage.openHomePage()
                .checkIsButtonSignOutVisible()
                .clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .deletePostWhilePresent(POST_TITLE);
        }

}
