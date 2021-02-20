package posts;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "Yaroslav6 Title of Post" + Util.getDateAndTimeFormated();

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
        .checkIsSuccessMessageDisplayed();
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
