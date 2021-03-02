package Pages;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;


import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.contains;

public class SinglePostPage extends ParentPage {

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private TextInput successMessageElement;

    @FindBy(xpath = ".//*[@data-original-title='Edit']")
    private Button EditPostButton;

    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private Button DeletePostButton;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private Button myProfileButton;

    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

//=========================================================

    public SinglePostPage chechIsRedirectToSinglePostPage() {
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , containsString(baseUrl + getRelativeUrl())); // = URL
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

    public CreatePostPage clickOnEditButton(){
        waitChatToBeHide();
        clickOnElement(EditPostButton);
        return new CreatePostPage(webDriver);
    }

// I WAS ILL
    public SinglePostPage checkIsRedirectToSinglePostPage(){
        waitChatToBeHide();
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , Matchers.containsString(baseUrl + "/post/"));
        return this;
    }



}

