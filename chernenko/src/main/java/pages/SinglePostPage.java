package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;

public class SinglePostPage extends ParentPage {

    @FindBy (xpath = ".//*[@class='alert alert-success text-center']")
    private TextInput successMessageElement;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private Button deleteButton;

    @FindBy(xpath = ".//h2")
    private TextInput postTitle;

    @FindBy(xpath = ".//strong[contains(text(),'Cherchenko Lena Title of Post')]")
    private TextInput titleOfPost;

    @FindBy (xpath = ".//*[@data-original-title='My Profile']")
    private Button profileButton;

    @FindBy(xpath = ".//*[@data-icon ='edit']")
    private Button editPost;


    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public SinglePostPage checkIsRedirectToSinglePostPage(){
        waitChatBeHied();
        Util.waitABit(2);
        Assert.assertThat("Invalid Page",webDriver.getCurrentUrl(), containsString(baseUrl+getRelativeUrl()));
        return this;
    }

    public SinglePostPage checkIsSuccessMessageDisplayed(){
        checkIsElementVisible(successMessageElement);
        return this;
    }


    public ProfilePage clickOnDeleteButton(){
        clickOnElement(deleteButton);
        logger.info("The post was deleted");
        return new ProfilePage(webDriver);
    }

    public ProfilePage clickOnProfileButton() {
        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }


    public EditPostPage clickOnEditButton() {
        clickOnElement(editPost);
        webDriverWait10.until(ExpectedConditions.elementToBeClickable(By.id("post-body")));
        return new EditPostPage(webDriver);
    }

}
