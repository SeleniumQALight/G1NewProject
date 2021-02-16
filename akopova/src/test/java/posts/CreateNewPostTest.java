package posts;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost() {

        loginPage.loginWithValidCreds()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectedOnCreatePostPage()
                .enterTitleIntoInputTitle("Inga Post Title")
                .enterTextIntoInputBody("Inga Post Body")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectedToSinglePostPage()
        ;


    }

    @After

    public void deletePost(){
    singlePostPage.clickOnHomePageLink();
               homePage.clickOnProfileIcon();
                myProfilePage.checkIsRedirectedOnMyProfilePage()
                              .clickOnPost();
                singlePostPage.checkIsRedirectedToSinglePostPage()
                        .clickOnDeleteIcon();
                myProfilePage.checkIsRedirectedOnMyProfilePage()
                        .checkForDeletionSuccess();

            ;

    }


}
