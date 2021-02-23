package post;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import pages.SinglePostPageWithNewPost;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "Cherchenko Lena Title of Post" + Util.getDateAndTimeFormated();
    final String POST_BODY_TEXT = "Cherchenko Post Body";

    @Test
    public void createNewPost() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                    .clickOnCreatePostButton()
                .checkIsRedirectedOnCreatePostPage()
                    .enterTitleIntoInputTitle(POST_TITLE)
                    .enterTextIntoInputBody(POST_BODY_TEXT)
                    .selectTextInDropDownRole ("Частное сообщение")
                .clickOnButtonSaveNewPost()
                    .checkIsRedirectToSinglePostPage()
                    .checkIsSuccessMessageDisplayed()
                ;

    }

    @Test
    public void createNewPostselectValueInDropDownRole() {
        loginPage.loginWithValidCred()
                    .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                    .checkIsRedirectedOnCreatePostPage()
                    .enterTitleIntoInputTitle(POST_TITLE)
                    .enterTextIntoInputBody(POST_BODY_TEXT)
                    .selectValueInDropDownRole("Group Message")
                .clickOnButtonSaveNewPost()
                    .checkIsRedirectToSinglePostPage()
                    .checkIsSuccessMessageDisplayed()
        ;

    }

    @After
    public void deletePost (){
        homePage
                .openHomePage()
                .checkIsButtonSignOutVisible()
                .clickOnProfileButton()
                .checkIsRedirectToProfilePage()
                    .deletePostWhilePresent(POST_TITLE)
        ;


    }

}