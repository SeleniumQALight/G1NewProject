package Pages;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.contains;

public class SinglePostPage extends ParentPage {

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessageElement;

    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement DeletePostButton;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private WebElement myProfileButton;

    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }

//=========================================================

    public SinglePostPage chechIsRedirectToSinglePostPage() {
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , containsString("https://qa-complex-app-for-testing.herokuapp.com/post/"));
        return this;
    }

    public SinglePostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessageElement);
        return this;
    }

//HOMEWORK 02-13

    public ProfilePage clickOnMyProfileButton() {
        waitChatToBeHide();
        clickOnElement(myProfileButton);
        return new ProfilePage(webDriver);
    }

    public ProfilePage clickOnDeletePostButton() {
        waitChatToBeHide();
        clickOnElement(DeletePostButton);
        return new ProfilePage(webDriver);
    }

// I WAS ILL
    public SinglePostPage checkIsRedirectToSinglePostPage(){
        waitChatToBeHide();
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , Matchers.containsString("https://qa-complex-app-for-testing.herokuapp.com/post/"));
        return this;
    }

}

