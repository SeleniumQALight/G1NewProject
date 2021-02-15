package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.Matchers.containsString;

public class SinglePostPage extends ParentPage {

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessageElement;
    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement deleteButton;

    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public SinglePostPage checkIsRedirectToSinglePostPage(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertThat("Invalid page", webDriver.getCurrentUrl() , containsString("https://qa-complex-app-for-testing.herokuapp.com/post/")); //содержит ли частичный урл
                //<!-- https://mvnrepository.com/artifact/org.hamcrest/hamcrest-all -->
                //        <dependency>
                //            <groupId>org.hamcrest</groupId>
                //            <artifactId>hamcrest-all</artifactId>
                //            <version>1.3</version>
                //
                //        </dependency>
                //        );  //нужно указать по какому принципу сравниваем (частичное сходство(урла содержит правильную часть))
        return this;
    }

    public SinglePostPage checkIsSuccessMessageDisplayed(){
        checkIsElementVisible(successMessageElement);
        return this;
    }

    public ProfilePage clickOnDeleteButton(){
        clickOnElement(deleteButton);
        return new ProfilePage(webDriver);
    }

}
