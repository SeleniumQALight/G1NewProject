package pages;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage {
    @FindBy (id = "post-title")
    private TextInput inputTitle;
    @FindBy (id = "post-body")
    private TextInput inputBody;
    @FindBy (xpath = ".//button[text()='Save New Post']")
    private Button buttonSaveNewPost;
    @FindBy(tagName = "select")
    private Select dropDownRole;


    public CreatePostPage (WebDriver webDriver){
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectedOnCreatePostPage() {
        waitChatToBeHide();
        Assert.assertEquals("Invalid page", baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
        return this;
    }

    public CreatePostPage enterTitleIntoInputTitle (String title){
        enterTextInToElement(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody (String body){
        enterTextInToElement(inputBody, body);
        return this;
    }

    public SinglePostPage clickOnButtonSaveNewPost (){
        clickOnElement(buttonSaveNewPost);
        return new SinglePostPage(webDriver);
    }

    @After
    public SinglePostPage clickOnDeleteButton(){

        clickOnDeleteButton();
        return new SinglePostPage(webDriver);
    }

    public LoginPage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();

        return loginPage;
    }

    public CreatePostPage selectTextInDropDownRole(String textInDD) {
        selectTextInDropDown(dropDownRole, textInDD);
        return this;
    }
}






