package pages;

import org.junit.After;
import org.junit.Assert;
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

    @FindBy (xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement deletePostButton;

    @FindBy (xpath = ".//h2")
    private WebElement postTitle;

    public CreatePostPage (WebDriver webDriver){
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectedOnCreatePostPage() {
        Assert.assertEquals("Invalid page", "https://qa-complex-app-for-testing.herokuapp.com/create-post", webDriver.getCurrentUrl());
        return this;
    }

    public CreatePostPage enterTitleIntoInputTitle (String title){
        //TODO will be changed in the future
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

    public SinglePostPage clickOnDeleteButton(){
        clickOnElement(deletePostButton);
        return  new SinglePostPage(webDriver);
    }

}
