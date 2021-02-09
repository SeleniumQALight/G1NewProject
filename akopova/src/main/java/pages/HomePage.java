package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPage{
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public boolean isButtonSignInVisible() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign In']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public boolean isErrorTextVisible() {
        try {
            return webDriver.findElement(By.xpath(".//*[@class='alert alert-danger text-center']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }


}
