package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends ParentPage{
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successMessage;
    @FindBy(xpath = ".//strong[contains(text(),'Yaroslav4 Title of Post')]")
    private WebElement yaroslavPost;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProfilePage checkIsFindPostButton(){
        checkIsElementVisible(yaroslavPost);
        return this;
    }

    public SinglePostPage clickOnPostButton(){
        clickOnElement(yaroslavPost);
        return new SinglePostPage(webDriver);
    }

    public ProfilePage checkIsSuccessMessageDisplayed(){
        checkIsElementVisible(successMessage);
        return this;
    }

    public ProfilePage checkIsRedirectedToProfilePage(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Valid home page", "https://qa-complex-app-for-testing.herokuapp.com/profile/auto" , webDriver.getCurrentUrl()); //"", expected, actual
        return this;
    }
}
