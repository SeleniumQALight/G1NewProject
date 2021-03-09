package posts;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PostEditing extends BaseTest {
    final String POST_TITLE = "Title Post" + Util.getDateAndTimeFormated();
    final String EDITED_POST_TITLE = "Edited post title" + Util.getDateAndTimeFormated();
    @Before

    public void createNewPost(){
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectedOnCreatePostPage()
                .enterTitleToInputTitle(POST_TITLE)
                .enterTextIntoInputBody("New Post body")
                .selectTextInDropDownRole("Частное сообщение")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToSinglePostPage()
                .checkIsSuccessMessageDisplayed()
                .clickOnProfileButton()
                .checkIsRedirectedToProfilePage()
                .checkIsPostWasAdded(POST_TITLE);
    }

    @Test
    public void editPost(){
        profilePage.clickOnCreatedPost(POST_TITLE);
        singlePostPage.checkIsRedirectToSinglePostPage();
        singlePostPage.clickOnEditButton();
        createPostPage.enterTitleToInputTitle(EDITED_POST_TITLE);
        createPostPage.clickButtonSaveUpdates();
        checkExpectedResult("Message 'Post successfully updated' is not visible",createPostPage.messagePostSuccessfullyUpdatedVisible());
        homePage.clickOnProfileButton();
        profilePage.checkIsRedirectedToProfilePage();
        profilePage.checkIsPostWasEdited(EDITED_POST_TITLE);
    }

    @After
    public void deletePost() {
        homePage.openHomePage();
        homePage.isButtonSignOutVisible();
        homePage.clickOnProfileButton();
        profilePage.checkIsRedirectedToProfilePage();
        profilePage.deletePostWhilePresent(EDITED_POST_TITLE);
    }
}
