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
 * LoginPage Class
 *
 * @author Leticia
 * @version 1.0
 */
public class LoginPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(_driver, Duration.ofMillis(1000));

    protected static final String inputEmailID = "email";
    protected static final String inputPasswordID = "password";
    protected static final String buttonRedirectToLoginXPath = "//a[@class='btn btn-secundario btn-xs'][normalize-space()='Iniciar sesión']";
    protected static final String buttonLoginXPath = "//button[normalize-space()='Ingresar']";
    protected static final String buttonLogOutID = "salir";
    protected static final String errorMessageClass = "form-feedback";
    protected static final String nameUserLogged= "//div[@class='saludo-registrado']//p[@class='txt-nombre']";
//    protected static final String locationClassName = "ubicacion";


    /**
     * Method to send the login request
     */
    public void redirectToLoginPage() {
        WebElement buttonRedirectToLogin = getWebElement(By.xpath(buttonRedirectToLoginXPath));
        wait.until(ExpectedConditions.elementToBeClickable(buttonRedirectToLogin));
        buttonRedirectToLogin.click();
    }

    /***
     * Method to positive login a user
     * @param email String with an existing user's email
     * @param password String with the correct password for the user's email
     */
    public void fillLoginUserForm(String email, String password) {
        WebElement inputEmail = getWebElement(By.id(inputEmailID));
        WebElement inputPassword = getWebElement(By.id(inputPasswordID));
        inputEmail.clear();
        inputPassword.clear();
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        wait.until(ExpectedConditions.textToBePresentInElementValue(inputEmail, email));
        wait.until(ExpectedConditions.textToBePresentInElementValue(inputPassword, password));
        inputEmail.sendKeys(Keys.ENTER);
        inputPassword.sendKeys(Keys.ENTER);
    }

    /**
     * Method to send the login request
     */
    public void loginUser() {
        WebElement buttonLogin = getWebElement(By.xpath(buttonLoginXPath));
        wait.until(ExpectedConditions.elementToBeClickable(buttonLogin));
        buttonLogin.click();
    }

    /**
     * Method to send the logout a user
     */
    public void logOutUser() {
        WebElement buttonLogOut = getWebElement(By.id(buttonLogOutID));
        wait.until(ExpectedConditions.elementToBeClickable(buttonLogOut));
        buttonLogOut.click();
    }

    /**
     * Method to catch the logged user's name
     * @return String userNameLogged
     * */
    public String getLoggedUserName() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(nameUserLogged)));
        WebElement userNameLogged = getWebElement(By.xpath(nameUserLogged));
        return userNameLogged.getText();
    }

    /**
     * Method to catch error message when login fails
     * @return String errorMessage
     */
    public String errorMessage() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(errorMessageClass)));
        WebElement message = getWebElement(By.className(errorMessageClass));
        return message.getText();
    }
}
