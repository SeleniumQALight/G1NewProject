package pages;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.Matchers.containsString;

public class SinglePostPage extends ParentPage {

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessageElement;

    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement DeletePostButton;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private WebElement profileButton;

    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public SinglePostPage chechIsRedirectToSinglePostPage() {
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , CoreMatchers.containsString("https://qa-complex-app-for-testing.herokuapp.com/post/"));
        return this;
    }

    public SinglePostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessageElement);
        return this;
    }

    //HOMEWORK 02-13
    public ProfilePage clickOnMyProfileButton() {
        waitChatToBeHide();
       /* try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace(); */

        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }

    public ProfilePage clickOnDeletePostButton() {
        /* try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } */
        waitChatToBeHide();
        clickOnElement(DeletePostButton);
        return new ProfilePage(webDriver);
    }

    public ProfilePage clickOnProfileButton() {
        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }
}