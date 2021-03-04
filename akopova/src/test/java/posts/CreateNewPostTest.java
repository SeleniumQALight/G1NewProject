package posts;

import baseTest.BaseTest;
import categories.SmokeTests;
import libs.MyUtil;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "Inga Post Title" + MyUtil.getDateAndTimeFormated();
    final String POST_BODY = "Inga Post Body" + MyUtil.getDateAndTimeFormated();
    @Category(SmokeTests.class)

    @Test
    public void createNewPost() {

        loginPage.loginWithValidCreds()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectedOnCreatePostPage()
                .enterTitleIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .selectElementInDropDown("One Person")
                //.selectTextInDropdownRole("Частное сообщение")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectedToSinglePostPage()
        .checkIsSuccessMessageDisplayed()
        .clickOnProfileButton()
        .checkIsRedirectedOnMyProfilePage()
        .checkIsPostWasAdded(POST_TITLE)
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
