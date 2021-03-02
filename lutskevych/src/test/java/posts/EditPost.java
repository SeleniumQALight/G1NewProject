package posts;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPost extends BaseTest {
    final String POST_TITLE = "Title Post" + Util.getDateAndTimeFormated();
    final String EDITED_POST_TITLE = "Edited post title" + Util.getDateAndTimeFormated();
    @Before

    public void createNewPost(){
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectedOnCreatePostPage()
                .enterTitleToInputTitle(POST_TITLE)
                .enterTextInToInputBody("Post body **********************")
                .selectTextInDropDownRole("Частное сообщение")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToSinglePostPage()
                .checkIsSuccessMessageDisplayed()
                .clickOnProfileButton()
                .checkIsSuccessRedirectToMyProfilePage()
                .checkIsPostWasAdded(POST_TITLE);
        }

     @Test
    public void editPost(){
        myProfilePage.clickOnCreatedPost(POST_TITLE);
        singlePostPage.checkIsRedirectToSinglePostPage();
        singlePostPage.clickOnButtonEdit();
//        createPostPage.checkIsRedirectedOnCreatePostPage();
        createPostPage.enterTitleToInputTitle(EDITED_POST_TITLE);
        createPostPage.clickButtonSaveUpdates();
        checkExpectedResult("Message 'Post successfully updated' is not visible",createPostPage.messagePostSuccessfullyUpdatedVisible());
        myProfilePage.clickOnMyProfileButton();
        myProfilePage.checkIsSuccessRedirectToMyProfilePage();
        myProfilePage.checkIsPostWasEdited(EDITED_POST_TITLE);
     }

    @After
    public void deletePost() {
        homePage.openHomePage();
        homePage.isButtonSignOutVisible();
        myProfilePage.clickOnMyProfileButton();
        myProfilePage.checkIsSuccessRedirectToMyProfilePage();
        myProfilePage.deletePostWhilePresent(EDITED_POST_TITLE);
    }

}
