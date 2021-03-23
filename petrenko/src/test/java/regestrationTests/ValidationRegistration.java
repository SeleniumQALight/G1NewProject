package regestrationTests;

import baseTest.BaseTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ValidationRegistration extends BaseTest {




    @Test
    public void validationRegistration(){
        loginPage.openLoinPage();
        loginPage.enterUserNameRegisterIn("12");
        loginPage.enterEmailRegisterIn("test.ua");
        loginPage.enterPasswordRegisterIn("123456789123");
        SoftAssertions softAssertions = new SoftAssertions();



    }



    }

//    @Step
//    public void checkErrors(String errors) {
//        String[] errorsArray = errors.split(";");
//
//        List<WebElement> actualErrorsList = webDriver.findElements(By.xpath(
//                ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));
//
//        Assert.assertEquals("Number of Messages", errorsArray.length, actualErrorsList.size());
//
//        SoftAssertions softAssertions = new SoftAssertions();
//
//        ArrayList<String> textFromErrors = new ArrayList<>();
//        for (WebElement element : actualErrorsList){
//            textFromErrors.add(element.getText());
//        }
//
//        for (int i = 0; i < errorsArray.length; i++) {
//            softAssertions.assertThat(errorsArray[i]).isIn(textFromErrors);
//        }
//        softAssertions.assertAll();
//
//    }