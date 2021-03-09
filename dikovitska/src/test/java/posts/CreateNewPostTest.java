package posts;

import baseTest.BaseTest;
import categories.SmokeTests;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "Yuliia Title of Post" + Util.getDateAndTimeFormated();
    @Category(SmokeTests.class)
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
