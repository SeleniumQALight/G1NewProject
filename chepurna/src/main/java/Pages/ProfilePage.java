//HOMEWORK 02-13
package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.CoreMatchers.containsString;

public class ProfilePage extends ParentPage{

   @FindBy(xpath = ".//*[contains(text(),'Chepurna Title of Post')] ")
    private WebElement postTitle;

   @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successDeletedMessageElement;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProfilePage chechIsRedirectToProfilePage(){
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , containsString("https://qa-complex-app-for-testing.herokuapp.com/profile")) ;
        return this;
    }
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public SinglePostPage clickOnPostTitle(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickOnElement(postTitle);
        return new SinglePostPage(webDriver);
    }

    public ProfilePage checkIsSuccessDeletedMessageDisplayed(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        checkIsElementVisible(successDeletedMessageElement);
        return this;
    }
}
