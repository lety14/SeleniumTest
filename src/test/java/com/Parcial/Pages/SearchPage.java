package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.beans.Visibility;
import java.time.Duration;

/**
 * SearchPage Class
 * @author Leticia
 * @version 1.0
 */
public class SearchPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(_driver, Duration.ofMillis(1000));

   protected static final String inputCityID = "ciudad";
   protected static final String searchButtonID = "btn-buscador";
   protected static final String searchResultsID = "card-list";
   protected static final String locationClassName = "ubicacion";

    /***
     * Method to find a city
     * @param city String with a city's name
     */
   public void findCity(String city){
       WebElement searchBox = getWebElement(By.id(inputCityID));
       searchBox.clear();
       searchBox.sendKeys(city);
       wait.until(ExpectedConditions.textToBePresentInElementValue(searchBox,city));
       searchBox.sendKeys(Keys.ENTER);
   }

    /**
     * Get results method
     * @return a string with the result of the search
     */
   public String getResults(){
       WebElement results = getWebElement(By.className(searchResultsID));
       wait.until(ExpectedConditions.visibilityOf(results));
       return results.getText();
   }

    /**
     * Get results method
     * @return a string with the result of the search
     */
    public String getLocation(){
        WebElement results = getWebElement(By.className(locationClassName));
        wait.until(ExpectedConditions.visibilityOf(results));
        return results.getText();
    }

    /***
     * Method searchAction that simulates the click on button search
     */
   public void searchAction(){
       WebElement search = getWebElement(By.id(searchButtonID));
       wait.until(ExpectedConditions.elementToBeClickable(search));
       search.click();
   }
}

