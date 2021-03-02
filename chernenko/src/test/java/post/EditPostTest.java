package post;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.Test;

public class EditPostTest extends BaseTest {
    final String POST_TITLE = "Cherchenko Lena Title of Post" + Util.getDateAndTimeFormated();
    final String POST_BODY_TEXT = "Cherchenko Post Body";
@Test
    public void editSinglePost(){
        loginPage.loginWithValidCred()
                    .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                    .checkIsRedirectedOnCreatePostPage()
                    .enterTitleIntoInputTitle(POST_TITLE)
                    .enterTextIntoInputBody(POST_BODY_TEXT)
                    .selectTextInDropDownRole("Сообщение для группы")
                .clickOnButtonSaveNewPost()
                    .checkIsRedirectToSinglePostPage()
                    .checkIsSuccessMessageDisplayed()
                .clickOnEditButton()
                    .checkIsRedirectToEditPostPage()
                    .editPostBodyAddText();

    }
}
