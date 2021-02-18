package posts;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "Inga Post Title";

    @Test
    public void createNewPost() {

        loginPage.loginWithValidCreds()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectedOnCreatePostPage()
                .enterTitleIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Inga Post Body")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectedToSinglePostPage()
        .checkIsSuccessMessageDisplayed()
        ;


    }

    @After

    public void deletePost(){
               homePage.openHomePage()
                       .checkIsButtonSignOutVisible()
                       .clickOnProfileButton()
                        ;
                myProfilePage.checkIsRedirectedOnMyProfilePage()
                              .deletePostTillWhilePresent(POST_TITLE);



    }


}
