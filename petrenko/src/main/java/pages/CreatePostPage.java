package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(id = "post-title")
    private WebElement inputTitle;
    @FindBy(id = "post-body")
    private WebElement inputBody;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement clickOnButtonSaveNewPost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectOnCreatePostPage() {
        Assert.assertEquals("Invalid page", "https://qa-complex-app-for-testing.herokuapp.com/create-post", webDriver.getCurrentUrl());

        return this;
    }

    public CreatePostPage enterTitleInToTitle(String title) {
     //TODO will be fixed
        try {
            System.out.println("Sleep");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        enterTextInToElement(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextInToInputBody(String text) {
        enterTextInToElement(inputBody, text);
        return this;
    }

    public SinglePostpage clickOnButtonSavePost(){
        clickOnElement(clickOnButtonSaveNewPost);
        return  new SinglePostpage(webDriver);
    }

}
