package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.Matchers.containsString;

public class SinglePostPage extends ParentPage{

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessageElement;

    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }
    public SinglePostPage checkIsRedirectToSinglePostPage() {
        Assert.assertThat("Invalid page", webDriver.getCurrentUrl(), containsString("https://qa-complex-app-for-testing.herokuapp.com/post/"));
        return this;
    }
    public SinglePostPage checkIsSuccessMessageDisplayed(){
        checkIsElementVisible(successMessageElement);
        return this;
    }
}
