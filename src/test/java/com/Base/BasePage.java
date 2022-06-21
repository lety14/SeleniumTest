package com.Base;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Base Class to model PageObjects
 * @author Leticia
 * @version 1.0
 */
public class BasePage {

    public static WebDriver _driver;
    protected static final String URL = "http://fe.deitech.online/";
    protected static final String returnToHome = "logo-lema";

    public BasePage(){
        if(_driver == null){
            System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        _driver = new ChromeDriver();
        _driver.manage().window().maximize();
        }
    }

    public void openApp(){
        _driver.get(URL);
    }

    public WebDriver getDriver() {
        return _driver;
    }

    public WebElement getWebElement(By locator){
        WebElement elem = null;
        try{
            elem = _driver.findElement(locator);
        }catch(Exception e){
            System.out.println("There is no element: "+ locator);
            System.out.println("Exception message: "+ e);
        }
        return elem;
    }

    public void returnToHome() {
        WebElement btnHome = getWebElement(By.className(returnToHome));
        btnHome.click();
    }
}
