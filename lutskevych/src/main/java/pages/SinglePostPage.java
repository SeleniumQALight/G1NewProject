package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import static org.hamcrest.Matchers.containsString;


public class SinglePostPage extends ParentPage {

    @FindBy(xpath = ".//*[contains(text(),'New post successfully created')]")
    private TextInput successMessageElement;
    @FindBy(xpath = ".//form[contains(@class,'delete')]")
    private Button deleteButton;
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private Button profileButton;
    @FindBy(xpath = ".//*[@data-icon='edit']")
    private WebElement buttonEdit;

    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public SinglePostPage checkIsRedirectToSinglePostPage(){
        waitChatToBeHide();
        Assert.assertThat("Invalid page",webDriver.getCurrentUrl(),containsString(baseUrl + getRelativeUrl()));
        return this;
    }

    public SinglePostPage checkIsSuccessMessageDisplayed(){
        checkIsElementVisible(successMessageElement);
        return this;
    }
    public MyProfilePage clickOnDeleteButton (){
        clickOnElement(deleteButton);
        return new MyProfilePage(webDriver);
    }


    public MyProfilePage clickOnProfileButton() {
        clickOnElement(profileButton);
        return new MyProfilePage(webDriver);
    }
    public void clickOnButtonEdit(){
        clickOnElement(buttonEdit);
    }
}
