package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    @FindBy(xpath = ".//*[text()='Create Post']")
    private WebElement createPostButton;
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement profileButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

        public boolean isButtonSignOutVisible(){
        /*
            try {
                return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
            } catch (Exception e) {
                return false;
            }

         */
            return isElementDisplayed(buttonSignOut);
        }

        public boolean isButtonSignOutNotVisible(){
         try{
            return !webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return true;
        }
        }
// return !isButtonSignOutVisible(); - как вариант замены try{} catch{}

        public HomePage checkIsButtonSignOutVisible() {
            checkIsElementVisible(buttonSignOut);
            return this;
        }

        public CreatePostPage clickOnCreatePostButton(){
        clickOnElement(createPostButton);
        return new CreatePostPage(webDriver);
        }

        public HomePage openHomePage(){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(!isButtonSignOutVisible()){
            loginPage.loginWithValidCred();
        }
        logger.info("Home Page was opened");
        return this;

        }

    public ProfilePage clickOnProfileButton() {
        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }
}
