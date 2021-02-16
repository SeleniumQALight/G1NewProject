package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.CoreMatchers.containsString;

public class SinglePostPage extends ParentPage{

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessageElement;

    @FindBy(xpath = ".//*[@data-original-title='Delete']")
   // @FindBy(xpath = ".//*[@class='svg-inline--fa fa-trash fa-w-14']")

    private WebElement deleteIcon;
    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public SinglePostPage checkIsRedirectedToSinglePostPage(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , containsString("https://qa-complex-app-for-testing.herokuapp.com/post/")
        );
                return this;
    }

    public SinglePostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessageElement);
        return this;
    }

    public void clickOnDeleteIcon(){
        try {
            clickOnElement(deleteIcon);
            Thread.sleep(1000);
            logger.info("Delete Post icon was clicked");
        } catch (InterruptedException e) {
            logger.error("can't click on Delete Post");
            e.printStackTrace();
        }
    }


}
