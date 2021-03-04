package posts;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.MyUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.SinglePostPage;

@RunWith(JUnitParamsRunner.class)
public class EditExistingPost extends BaseTest {
    final String POST_TITLE = "Inga Post Title" + MyUtil.getDateAndTimeFormated();
    final String POST_TITLE_UPD = "Inga Post Title" + MyUtil.getDateAndTimeFormated() + " updated";
    final String POST_BODY = "Inga Post Body" + MyUtil.getDateAndTimeFormated();

@Before
public void preparePostToEdit() throws Exception{
    logger.info("---- editing post in " + testName.getMethodName() + " started -----");



    loginPage.loginWithValidCreds()
            .checkIsButtonSignOutVisible()
            .clickOnCreatePostButton()
            .checkIsRedirectedOnCreatePostPage()
            .enterTitleIntoInputTitle(POST_TITLE)
            .enterTextIntoInputBody(POST_BODY)
            .selectElementInDropDown("Group Message")
            .clickOnButtonSaveNewPost()
            .checkIsRedirectedToSinglePostPage()
            .checkIsSuccessMessageDisplayed()
            .clickOnProfileButton()
            .checkIsRedirectedOnMyProfilePage()
            .checkIsPostWasAdded(POST_TITLE)
    ;

        }

    @Test
    public void editPost(){

           SinglePostPage singlePostPage = new SinglePostPage(webDriver);

            myProfilePage.clickOnPost();
            singlePostPage.waitChatToBeHide();

            singlePostPage.checkIsRedirectedToSinglePostPage();
            singlePostPage.clickOnEditIcon();
            singlePostPage.waitChatToBeHide();
            singlePostPage.checkIsRedirectedToSinglePostPage();
            singlePostPage.getPostTitle();
            singlePostPage.updatePostTitle();
            singlePostPage.clickSaveButton()
            .waitChatToBeHide();
            singlePostPage.checkIfTheUpdateNotificationPresent()
            .waitChatToBeHide();

            singlePostPage.checkIfTheTitleWasUpdated();
            singlePostPage.clickOnProfileButton()
                    .waitChatToBeHide();

    }

@After
public void deletePost(){
    homePage.openHomePage()
            .checkIsButtonSignOutVisible()
            .clickOnProfileButton()
    ;
    myProfilePage.checkIsRedirectedOnMyProfilePage()
            .deletePostTillWhilePresent(POST_TITLE_UPD);



}

}
