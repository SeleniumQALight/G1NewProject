package posts;

import baseTest.BaseTest;
import categories.SmokeTests;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "Yaroslav6 Title of Post" + Util.getDateAndTimeFormated();
    @Category(SmokeTests.class)
    @Test
    public void createNewPost(){
        loginPage.loginWithValidCred()
        .checkIsButtonSignOutVisible()
        .clickOnCreatePostButton()
                .checkIsRedirectedOnCreatePostPage() //переход на другую страницу
        .enterTitleToInputTitle(POST_TITLE)
        .enterTextIntoInputBody("Post body")
                .selectTextInDropDownRole("Частное сообщение")
        .clickOnButtonSaveNewPost()
                .checkIsRedirectToSinglePostPage()
        .checkIsSuccessMessageDisplayed()
        .clickOnProfileButton()
        .checkIsPostWasAdded(POST_TITLE)
        ;
    }

    @After
    public void deletePost(){
        //опен хомпейдж, убедиться что  хом пейдже, кликнуть на профайл кнопку, найти элемент по посту, кликнуть на пост (и удалить)
        homePage .openHomePage()
                .checkIsButtonSignOutVisible()
                .clickOnProfileButton()
                .checkIsRedirectedToProfilePage()
                .deletePostWhilePresent(POST_TITLE);


    }
}
