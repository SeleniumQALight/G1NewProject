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
       //ProfilePage=
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
                 .checkISPostWasAdded(POST_TITLE)

                 .clickOnPostTitle()
                 .chechIsRedirectToSinglePostPage()
                 .clickOnDeletePostButton()
                 .checkIsSuccessDeletedMessageDisplayed();

    }

}
