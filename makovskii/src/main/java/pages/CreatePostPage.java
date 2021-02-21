package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy(id="post-title")
    private WebElement inputTitle;
    @FindBy(id = "post-body")
    private WebElement inputBody;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectedOnCreatePostPage() {
        waitChatToBeHide();
        Assert.assertEquals("Invalid page"
                ,"https://qa-complex-app-for-testing.herokuapp.com/create-post"
                , webDriver.getCurrentUrl());
        return this;
    }

    public CreatePostPage enterTitleInToInputTitle(String title){
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextInToInputBody(String text){
        enterTextIntoElement(inputBody, text);
        return this;
    }

    public SinglePostPage clickOnButtonSaveNewPost(){
        clickOnElement(buttonSaveNewPost);
        return new SinglePostPage(webDriver);
    }

    public CreatePostPage selectTextInDropdownRole(String textInDD) {
        selectTextInDropDown(dropDownRole, textInDD);
        return this;
    }
}
