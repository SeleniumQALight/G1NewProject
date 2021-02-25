package posts;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
final String POST_TITLE = "Kosenko Title of Post" + Util.getDateAndTimeFormated();

    @Test
    public void createNewPost() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectedOnCreatePostPage()
                .enterTitleIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Kosenko Post Body")
                .selectTextInDropDownRole("Частное сообщение")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToSinglePostPage()
                .checkIsSuccessMessageDisplayed()
                .clickOnProfileButton()
        .checkIsRedirectedToProfilePage()
        .checkIsPostWasAdded(POST_TITLE)
        ;
    }
    @After

    public void deletePost(){
        singlePostPage.clickOnMyProfileButton()
                .checkIsRedirectedToProfilePage()
                .clickOnPostTitle()
                .checkIsRedirectToSinglePostPage()
                .clickOnDeletePostButton()
                .checkIsSuccessDeletedMessageDisplayed();
    }
}