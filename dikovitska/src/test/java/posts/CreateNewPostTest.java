package posts;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "Taras Title of Post";
    @Test
    public void createNewPost(){
        loginPage.loginWithValidCred()
        .checkIsButtonSignOutVisible()
        .clickOnCreatePostButton()
        .checkIsRedirectedOnCreatePostPage()
                   .enterTitleInToInputTitle(POST_TITLE)
                   .enterTextInToInputBody("Post body")
                   .clickOnButtonSaveNewPost()
                 .checkIsRedirectToSinglePostPage()


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
