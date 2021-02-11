package posts;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost(){
        loginPage.loginWithValidCred()
        .checkIsButtonSignOutVisible()
        .clickOnCreatePostButton()
                .checkIsRedirectedOnCreatePostPage() //переход на другую страницу
        .enterTitleToInputTitle("Yaroslav Title of Post")
        .enterTextIntoInputBody("Post body")
        .clickOnButtonSaveNewPost()
                .checkIsRedirectToSinglePostPage()
        .checkIsSuccessMessageDisplayed();
    }

    @After
    public void deletePost(){
        //опен хомпейдж, убедиться что  хом пейдже, кликнуть на профайл кнопку, кликнуть на пост, найти элемент по посту (и удалить)
    }
}
