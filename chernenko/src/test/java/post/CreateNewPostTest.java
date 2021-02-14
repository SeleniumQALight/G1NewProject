package post;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CreatePostPage;
import pages.ParentPage;

import static org.hamcrest.Matchers.containsString;

public class CreateNewPostTest extends BaseTest {



    @Test
    public void createNewPost (){
        loginPage.loginWithValidCred()
                     .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                     .checkIsRedirectedOnCreatePostPage ()
                     .enterTitleIntoInputTitle("Cherchenko Lena Title of Post")
                     .enterTextIntoInputBody("Cherchenko Post Body")
                .clickOnButtonSaveNewPost()
                    .checkIsRedirectToSinglePostPage();

    }

    @After
    public void deletePost(){
        CreatePostPage.clickOnDeleteButton();
    }
}
