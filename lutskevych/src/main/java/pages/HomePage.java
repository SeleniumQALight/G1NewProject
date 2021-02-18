package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    @FindBy(xpath =".//button[contains(text(),'Sign Out')]" )
    private WebElement buttonSignOut;
    @FindBy(xpath = ".//a[contains(text(),'Create Post')]")
    private WebElement createPostButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }
    public boolean isButtonSignOutVisible(){
//        try {
//            return webDriver.findElement(By.xpath(".//button[contains(text(),'Sign Out')]")).isDisplayed();
//        }catch (Exception e){
//            return false;
//        }
       return isElementDisplayed(buttonSignOut);
    }
    public HomePage checkIsButtonSignOutVisible(){
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    public CreatePostPage clickOnCreatePostButton(){
        clickOnElement(createPostButton);
        return new CreatePostPage(webDriver);
    }


    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(!isButtonSignOutVisible()){
           loginPage.loginWithValidCred();
        }
        logger.info("Home Page was opened ");
        return this;
    }
}
