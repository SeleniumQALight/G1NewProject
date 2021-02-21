package pages;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy (id = "post-title")
    private WebElement inputTitle;

    @FindBy (id = "post-body")
    private WebElement inputBody;

    @FindBy (xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy (tagName = "select")
    private WebElement dropDownRole;

    @FindBy (id = "select1")
    private WebElement typeOfPostList;

    @FindBy (xpath = ".// select [@id = 'select1']")
    private WebElement valueOfList;


    public CreatePostPage (WebDriver webDriver){
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectedOnCreatePostPage() {
        waitChatBeHied();
        Assert.assertEquals("Invalid page", "https://qa-complex-app-for-testing.herokuapp.com/create-post", webDriver.getCurrentUrl());
        return this;
    }

    public CreatePostPage enterTitleIntoInputTitle (String title){
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody (String body){
        enterTextIntoElement(inputBody, body);
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

    public CreatePostPage selectTextInDropDownRole(String textInDD) {
        selectTextInDropDown(dropDownRole, textInDD);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole (String value){
        clickOnElement(typeOfPostList);
        selectValueInDropDown(valueOfList, value);
        return this;
    }
}
