package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import static org.hamcrest.Matchers.containsString;

public class SinglePostPage extends ParentPage {

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private TextInput successMessageElement;
    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private Button deleteButton;
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private Button profileButton;
    @FindBy(xpath = ".//*[@data-icon='edit']")
    private WebElement editButton;

    public SinglePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public SinglePostPage checkIsRedirectToSinglePostPage(){
        waitChatToBeHide();
        Assert.assertThat("Invalid page", webDriver.getCurrentUrl() , containsString(baseUrl + getRelativeUrl())); //содержит ли частичный урл
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

    public ProfilePage clickOnProfileButton() {
        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }
    public void clickOnEditButton(){
        clickOnElement(editButton);
    }
}
