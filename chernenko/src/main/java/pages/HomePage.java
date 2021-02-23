package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

public class HomePage extends ParentPage{
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private Button buttonSignOut;

    @FindBy (xpath =".//*[text()='Create Post']")
    private Button clickOnCreatePostButton;

    @FindBy (xpath = ".//*[@data-original-title='My Profile']")
    private Button profileButton;

    public HomePage(WebDriver webDriver){
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public  HomePage checkIsButtonSignOutVisible(){
        checkIsElementVisible(buttonSignOut);
        return  this;
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }



    public CreatePostPage clickOnCreatePostButton (){
        clickOnElement(clickOnCreatePostButton);
        return new CreatePostPage(webDriver);
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSignOutVisible()){
            loginPage.loginWithValidCred();

        }
        logger.info("Home page was opened");
        return this;
    }

    public ProfilePage clickOnProfileButton() {
        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }
}
