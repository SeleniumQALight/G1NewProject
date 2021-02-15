package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(xpath = ".//input[@name = 'title']")
    private WebElement inputTitle;
    @FindBy(xpath = ".//textarea[@name = 'body']")
    private WebElement inputBody;
    @FindBy(xpath = ".//button[contains(text(),'Save New Post')]")
    private WebElement buttonSveNewPost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectedOnCreatePostPage(){
        Assert.assertEquals("Invalid page", "https://qa-complex-app-for-testing.herokuapp.com/create-post",webDriver.getCurrentUrl());
        return this;
    }
    public CreatePostPage enterTitleToInputTitle(String title){
        // TODO will be fixed
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextInToInputBody(String text){
        enterTextIntoElement(inputBody, text);
        return this;
    }
    public SinglePostPage clickOnButtonSaveNewPost(){
        clickOnElement(buttonSveNewPost);
        return new SinglePostPage(webDriver);
    }
}
