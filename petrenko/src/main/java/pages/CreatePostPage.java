package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    public String title;

    @FindBy(id = "post-title")
    private WebElement inputTitle;
    @FindBy(id = "post-body")
    private WebElement inputBody;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement clickOnButtonSaveNewPost;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectOnCreatePostPage() {
        waitChatToBeHide();
        Assert.assertEquals("Invalid page", baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());

        return this;
    }

    public CreatePostPage enterTitleInToTitle(String title) {
        enterTextInToElement(inputTitle, title);
        this.title = title;
        return this;
    }

    public CreatePostPage enterTextInToInputBody(String text) {
        enterTextInToElement(inputBody, text);
        return this;
    }

    public SinglePostPage clickOnButtonSavePost() {
        clickOnElement(clickOnButtonSaveNewPost);
        return new SinglePostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownRole(String textInDD) {
        selectTextInDropDown(dropDownRole, textInDD);
        return this;
    }
}
