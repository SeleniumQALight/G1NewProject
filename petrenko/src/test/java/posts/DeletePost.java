package posts;

import baseTest.BaseTest;
import org.junit.Test;

public class DeletePost extends BaseTest {

   @Test
    public void deletePost() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnMyProfileButton()
                .checkIsRedirectOnMyProfilePage()
                .checkIsPostWithTheValidTitleIsVisible()
                .clickOnPostWithTheValidTitle()
                .checkIsRedirectOnSinglePostPage()
               .clickOnDeletePostButton();

    }

}
