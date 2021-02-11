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
        .checkIsRedirectedOnCteatePostPage()
        .enterTitleInToInputTitle("Chepurna Title of Post")
                .enterTextInToInputBody("Post body")
                .clickOnButtonSaveNewPost();
    }
    @After
    public void deletePost(){

    }


}
