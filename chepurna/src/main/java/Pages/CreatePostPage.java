package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage {

    @FindBy(id= "post-title")
    private TextInput inputTitle;

    @FindBy(id= "post-body")
    private TextInput inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private Button buttonSaveNewPost;

    @FindBy(xpath = ".//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    @FindBy(id = "select1")
    private WebElement dropDownRole;

    final String textDDLocator = ".//*[text()='%s']";   //Locator like String

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

//URL of this page
    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

//=========================================================

    public CreatePostPage checkIsRedirectedOnCteatePostPage() {
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl() // = URL
                , webDriver.getCurrentUrl());
        return this;
    }

    public CreatePostPage enterTitleInToInputTitle (String title){
        waitChatToBeHide();
        enterTextInToElement(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextInToInputBody (String text){
        enterTextInToElement(inputBody, text);
        return this;
    }

    public SinglePostPage clickOnButtonSaveNewPost(){
        clickOnElement(buttonSaveNewPost);
        return new SinglePostPage(webDriver);
    }

    public SinglePostPage clickOnButtonSaveUpdates(){
        clickOnElement(buttonSaveUpdates);
        return new SinglePostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownRole(String textInDD) {
        selectTextInDropDown(dropDownRole, textInDD);
        return this;
    }

//DROP DOWN (HOMEWORK 02-20)
    public CreatePostPage selectTextInDropDownLikeManual(String textDD){
        clickOnElement(dropDownRole);
        checkIsElementVisible(webDriver.findElement(By.xpath(String.format(textDDLocator, textDD))));
        clickOnElement(webDriver.findElement(By.xpath(String.format(textDDLocator, textDD))));
        return this;
    }

}

