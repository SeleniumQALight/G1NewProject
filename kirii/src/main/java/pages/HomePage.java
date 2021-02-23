package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

public class HomePage extends ParentPage {

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private Button buttonSignOut;

    @FindBy(xpath = ".//*[text()='Create Post']")
    private Button createPostButton;
    @FindBy(xpath = ".//a[contains(text(),'Complex app for testing - QA')]")
    private Button createHomeButton;
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private Button profileButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public boolean isButtonSignOutVisible() {
//        try {
//            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isEnabled();
//        }catch (Exception e){
//            return false;
//        }
        return isElementDisplayed(buttonSignOut);
    }

    public HomePage checkIsButtonSignOutVisible(){
        checkIsElementVisible(buttonSignOut);
        return this;
    }


    /*public boolean isButtonSignOutNotVisible(){
        return !isButtonSignOutVisible();
    }*/
    public CreatePostPage clickOnCreatePostButton(){
        clickOnElement(createPostButton);
        return new CreatePostPage(webDriver);
    }

    public HomePage clickOnHomePage(){
        clickOnElement(createHomeButton);
        return new HomePage(webDriver);
    }
    public ProfilePage clickOnProfileButton(){
        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }

    public HomePage checkIsRedirectedOnHomePage(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Valid home page", baseUrl + getRelativeUrl() , webDriver.getCurrentUrl()); //"", expected, actual
        return this;
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSignOutVisible()){
            loginPage.loginWithValidCred();
        }
        logger.info("Home Page was opened");
        return this;
    }
}
