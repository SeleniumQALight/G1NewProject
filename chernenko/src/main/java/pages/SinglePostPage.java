package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;

public class SinglePostPage extends ParentPage {

    @FindBy (xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessageElement;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement deleteButton;

    @FindBy(xpath = ".//h2")
    private WebElement postTitle;

    @FindBy(xpath = ".//strong[contains(text(),'Cherchenko Lena Title of Post')]")
    private WebElement titleOfPost;

    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public SinglePostPage checkIsRedirectToSinglePostPage(){
        waitChatBeHied();
       // Util.waitABit(2);
        Assert.assertThat("Invalid Page",webDriver.getCurrentUrl(), containsString("https://qa-complex-app-for-testing.herokuapp.com/post/"));
        return this;
    }

    public SinglePostPage checkIsSuccessMessageDisplayed(){
        checkIsElementVisible(successMessageElement);
        return this;
    }

    public SinglePostPage clickOnTitleOfPost(){
        clickOnElement(titleOfPost);
        return new SinglePostPage(webDriver);
    }

    public ProfilePage clickOnDeleteButton(){
        clickOnElement(deleteButton);
        logger.info("The post was deleted");
        return new ProfilePage(webDriver);
    }

}
