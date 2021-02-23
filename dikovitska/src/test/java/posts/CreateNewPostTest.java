package posts;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "Yuliia Title of Post" + Util.getDateAndTimeFormated();
    @Test
    public void createNewPost(){
        loginPage.loginWithValidCred()
        .checkIsButtonSignOutVisible()
        .clickOnCreatePostButton()
        .checkIsRedirectedOnCreatePostPage()
                   .enterTitleInToInputTitle(POST_TITLE)
                   .enterTextInToInputBody("Post body")
                   .selectTextInDropdownRole("Частное сообщение")
                   .clickOnButtonSaveNewPost()
                 .checkIsRedirectToSinglePostPage()
                 .checkIsSuccessMessageDisplayed()
                 .clickOnProfileButton()
                 .checkIsRedirectToProfilePage()
                 .checkIsPostWasAdded(POST_TITLE)

        ;

    }
    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .checkIsButtonSignOutVisible()
                .clickOnProfileButton()
                .checkIsRedirectToProfilePage()
                .deletePostWhilePresent(POST_TITLE);

    }
}
