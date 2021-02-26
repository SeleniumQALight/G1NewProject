package posts;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import pages.SinglePostPage;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "Iryna Lutskevych Title Post 222222" + Util.getDateAndTimeFormated();
    @Test
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
                .checkIsPostWasAdded(POST_TITLE)

          ;
       }

      @After
    public void deletePost(){
           homePage.openHomePage();
           homePage.isButtonSignOutVisible();
           myProfilePage.clickOnMyProfileButton();
           myProfilePage.checkIsSuccessRedirectToMyProfilePage();
           myProfilePage.deletePostWhilePresent(POST_TITLE);
//           myProfilePage.clickOnCreatedPost(POST_TITLE);
//           singlePostPage.checkIsRedirectToSinglePostPage();
//           singlePostPage.clickOnDeleteButton();
//           myProfilePage.checkIsSuccessRedirectToMyProfilePage();
//           myProfilePage.checkPostWasDeleted("Iryna Lutskevych Title Post 222222");
//           myProfilePage.checkIsMessageAboutSuccessDeletionIsPresent();


       }

   }
