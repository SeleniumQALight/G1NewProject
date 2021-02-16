package posts;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {

    @Test
    public void createNewPost() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectOnCreatePostPage()
                .enterTitleInToTitle(TestData.VALID_TITLE)
                .enterTextInToInputBody("bshzbzx")
                .clickOnButtonSavePost()
                .checkIsRedirectOnSinglePostPage()
                .checkIsSuccessMessageDisplayed();

    }

    @After
    public void deletePost() {
        homePage.clickTextBackToStartHomePage()
                .clickOnMyProfileButton()
                .checkIsRedirectOnMyProfilePage()
                .checkIsPostWithTheValidTitleIsVisible()
                .clickOnPostWithTheValidTitle()
                .checkIsRedirectOnSinglePostPage()
                .clickOnDeletePostButton()
                .clickOnMyProfileButton()
                .checkIsPostWithTheValidTitleIsNotVisible();

    }


}
