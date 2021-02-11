package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;

public class SinglePostpage extends  ParentPage {
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessageElement;

    public SinglePostpage(WebDriver webDriver) {
        super(webDriver);
    }

    public SinglePostpage checkIsRedirectOnSinglePostPage() {
        Assert.assertThat("Invalid page",  webDriver.getCurrentUrl(), containsString("https://qa-complex-app-for-testing.herokuapp.com/post"));

        return this;
    }

    public  SinglePostpage checkIsSuccessMessageDisplayed(){
        checkIsElementVisible(successMessageElement);
        return  this;
    }
}
