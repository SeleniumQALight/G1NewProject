package regestrationTests;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;


@Epic("Allure examples")
@Feature("Junit 4 support")

public class ValidRegistrationByClickSingUpForOurAppButton extends BaseTest {


    @Test

    public void validRegistrationByClickSingUpForOurAppButton() {
        String validLogin = loginPage.createValidLoginBySize(3);
        String validEmail = loginPage.createValidEmail();
        String validPassword = loginPage.createValidPasswordBySize(12);

        loginPage.fillRegisterFormAndSubmit(validLogin, validEmail, validPassword)
                .checkIsPasswordIsNotVisible(validPassword)
                .checkErrorOfMessagesAfterValidRegisterIn()
                .clickSingUpForOurAppButton()
                .checkIsRedirectOnHomePage()
                .registrationFormIsNotVisible()
                .messageHelloNewUserIsVisible(validLogin)
                .checkIsSearchButtonVisible()
                .checkIsChatButtonVisible()
                .checkIsAvatarButtonVisible()
                .checkIsUserNameButtonVisible();

    }


}
