package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPage {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }
    public boolean isButtonSignOutVisible(){
        try {
            return webDriver.findElement(By.xpath(".//button[contains(text(),'Sign Out')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }



}
