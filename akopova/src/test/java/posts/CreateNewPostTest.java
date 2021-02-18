package posts;

import baseTest.BaseTest;
import libs.MyUtil;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "Inga Post Title" + MyUtil.getDateAndTimeFormated();

    @Test
    public void createNewPost() {

        loginPage.loginWithValidCreds()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectedOnCreatePostPage()
                .enterTitleIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Inga Post Body")
                .selectTextInDropdownRole("Частное сообщение")
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
