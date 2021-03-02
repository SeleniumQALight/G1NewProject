package posts;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostAndChangeTitlePest extends BaseTest {
    final String POST_TITLE = "Chepurna Title of Post " + Util.getDateAndTimeFormated();
    final String POST_TITLE_NEW = "NEW Chepurna Title of Post " + Util.getDateAndTimeFormated();
    @Test
    public void CreatePostAndChangeTitle(){
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectedOnCteatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextInToInputBody("Post body")
                .clickOnButtonSaveNewPost()
                .chechIsRedirectToSinglePostPage()
                .checkIsSuccessMessageDisplayed()
                .clickOnEditButton()
                .enterTitleInToInputTitle(POST_TITLE_NEW)
                .clickOnButtonSaveUpdates()
                .chechIsRedirectToSinglePostPage()
                .checkIsSuccessMessageDisplayed()
                .clickOnMyProfileButton()
                .chechIsRedirectToProfilePage()
                .checkISPostWasAdded(POST_TITLE_NEW, 1)
                .checkISPostWasAdded(POST_TITLE, 0);
    }

    @After
    public void deletePost(){
        homePage.openHomePage()
                .checkIsButtonSignOutVisible()
                .clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .deletePostWhilePresent(POST_TITLE_NEW)
                .deletePostWhilePresent(POST_TITLE);
    }

}
