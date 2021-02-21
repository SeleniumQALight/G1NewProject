package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(id= "post-title")
    private WebElement inputTitle;

    @FindBy(id= "post-body")
    private WebElement inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }


    public CreatePostPage checkIsRedirectedOnCteatePostPage() {
        Assert.assertEquals("Invalid page"
                , "https://qa-complex-app-for-testing.herokuapp.com/create-post"
                , webDriver.getCurrentUrl());
    return this;
    }
//TODO to fix
    public CreatePostPage enterTitleInToInputTitle (String title){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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


    public CreatePostPage selectTextInDropDownRole(String textInDD) {
        selectTestinDropDown(dropDownRole, textInDD);
        return this;
    }
}
