package posts;

import baseTest.BaseTest;
import categories.SmokeTests;
import io.qameta.allure.*;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
@Epic("Allure examples")
@Feature("Junit 4 support")

public class CreateNewPostTest extends BaseTest {
//    @Description("Some detailed test description")
//    @Story("Base support for bdd annotations")
//    @Link("https://example.org")
//    @Link(name = "allure", type = "mylink")
//    @Issue("123")
//    @Issue("432")
//    @Severity(SeverityLevel.CRITICAL)



    final String POST_TITLE = "Yura Title of post." + Util.getDateAndTimeFormated();

    @Category(SmokeTests.class)



    @Test
    public void createNewPost() {



        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectOnCreatePostPage()
                .enterTitleInToTitle(POST_TITLE)
                .enterTextInToInputBody("bshzbzx")
                .clickValueInDropDownInCreatePost("Частное сообщение")
                .clickOnButtonSavePost()
                .checkIsRedirectOnSinglePostPage()
                .checkIsSuccessMessageDisplayed()
                .clickOnProfileButton()
                .checkIsRedirectOnMyProfilePage()
                .checkIsPostWasAdded(POST_TITLE);


    }


    @After
    public void deletePost() {

        homePage.openHomePage()
                .checkIsButtonSignOutVisible()
                .clickOnMyProfileButton()
                .checkIsRedirectOnMyProfilePage().deletePostWhilePresent(POST_TITLE);
//                .checkIsPostWithTheValidTitleIsVisible()
//                .clickOnPostWithTheValidTitle()
//                .checkIsRedirectOnSinglePostPage()
//                .clickOnDeletePostButton()
//                .clickOnMyProfileButton()
//                .checkIsPostWithTheValidTitleIsNotVisible(POST_TITLE);

    }


}
