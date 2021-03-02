package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage {

    public String title;

    @FindBy(id = "post-title")
    private TextInput inputTitle;
    @FindBy(id = "post-body")
    private TextInput inputBody;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private Button clickOnButtonSaveNewPost;
    @FindBy(tagName = "select")
    private Button dropDownRole;
    @FindBy(name = "select1")
    private Button dropDownInCreatePost;
    String valueOfDropDownInCreatePost = ".//*[text() = '%s']";

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

    public CreatePostPage clickValueInDropDownInCreatePost(String valueOfDopDown){
        dropDownInCreatePost.isDisplayed();
        dropDownInCreatePost.click();
        webDriver.findElement(By.xpath(String.format(valueOfDropDownInCreatePost,valueOfDopDown))).isDisplayed();
        webDriver.findElement(By.xpath(String.format(valueOfDropDownInCreatePost,valueOfDopDown))).click();
        return new CreatePostPage(webDriver);
    }

}
