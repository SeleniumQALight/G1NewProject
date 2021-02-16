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
        .enterTitleToInputTitle("Yaroslav4 Title of Post")
        .enterTextIntoInputBody("Post body")
        .clickOnButtonSaveNewPost()
                .checkIsRedirectToSinglePostPage()
        .checkIsSuccessMessageDisplayed();
    }

    @After
    public void deletePost(){
        //опен хомпейдж, убедиться что  хом пейдже, кликнуть на профайл кнопку, найти элемент по посту, кликнуть на пост (и удалить)
        homePage.clickOnHomePage()
        .checkIsRedirectedOnHomePage();
        createPostPage.clickOnProfileButton()
                .checkIsRedirectedToProfilePage()
        .checkIsFindPostButton()
        .clickOnPostButton()
                .checkIsRedirectToSinglePostPage()
        .clickOnDeleteButton()
                .checkIsRedirectedToProfilePage()
        .checkIsSuccessMessageDisplayed()
        ;


    }
}
