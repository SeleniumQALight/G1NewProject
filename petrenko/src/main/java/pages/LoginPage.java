package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage{


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoinPage(){

        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was open.");
        }catch (Exception e)
        {
            logger.info("Can not open loginPage.");
            Assert.fail("Can not open loginPage.");
        }

    }

    public void enterLoginSignIn(String login) {
        try{
            WebElement inputLogin = webDriver.findElement(By.xpath(".//form[@action = '/login']//input[@name = 'username']"));
            inputLogin.clear();
            inputLogin.sendKeys(login);
            logger.info(login + " was inputted in to login input.");
        }catch (Exception e){
            logger.error("Can not with login input.");
            Assert.fail("Can not with login input.");
        }
    }

    public void enterPassWordSignIn(String passWord) {
        try{
            WebElement inputLogin = webDriver.findElement(By.xpath(".//form[@action = '/login']//input[@name = 'password']"));
            inputLogin.clear();
            inputLogin.sendKeys(passWord);
            logger.info(passWord + " was inputted in to passWord input.");
        }catch (Exception e){
            logger.error("Can not work with passWord input.");
            Assert.fail("Can not work with passWord input.");
        }
    }

    public void clickButtonSignIn() {
        try {
            WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
            buttonSignIn.click();
        }catch (Exception e){
            logger.error("Can not work with button SignIn.");
            Assert.fail("Can not work with button SignIn.");
        }
    }
}
