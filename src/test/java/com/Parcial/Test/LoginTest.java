package com.Test;

import com.Base.BasePage;
import com.Pages.LoginPage;
import com.Pages.SearchPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.extentReports.ExtentFactory;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;



/**
 * LoginTest Class
 */
public class LoginTest {
    private static WebDriver driver;
    static ExtentSparkReporter spark = new ExtentSparkReporter("./target/Spark.html");
    static ExtentReports extent;
    ExtentTest test;

    /**
     * Method to initialize the driver before run the tests
     */
    @BeforeAll
    static void setUp() {
        BasePage _basePage = new BasePage();
        _basePage.openApp();
        driver = _basePage.getDriver();
        extent = ExtentFactory.getInstance();
        extent.attachReporter(spark);
    }

    /**
     * Method to test the Log In success case
     * Steps:
     * 1. Complete the inputs of the login form with the user's name and password.
     * 2. Click the login button.
     * 3. Check that the user was successfully logged in.
     */
    @Test
    @Tag("smoke")
    @DisplayName("Test Positive Login Digital Booking")
    public void testLogin() {
        test = extent.createTest("Test Positive Login Digital Booking");
        test.log(Status.INFO, "Login Test Started!");
        LoginPage page = new LoginPage();
        page.redirectToLoginPage();
        test.log(Status.PASS, "Open Login Page");
        page.fillLoginUserForm("danytejerina@gmail.com", "daniel123");
        test.log(Status.PASS, "Add Login Credentials");
        page.loginUser();
        test.log(Status.PASS, "Send Request To Verify Credentials");
        String result = page.getLoggedUserName();
        assertTrue(result.equals("Daniel Tejerina"));
        test.log(Status.PASS, "Check Successfully Login");
        page.logOutUser();
        test.log(Status.PASS, "Logout User Success");
    }

    /**
     * Method to test the Log In Negative case
     * Steps:
     * 1. Complete the inputs of the login form with incorrect user's name and password.
     * 2. Click the login button.
     * 3. Check that the user was not successfully logged in.
     */
    @Test
    @Tag("smoke")
    @DisplayName("Test Negative Login Digital Booking")
    public void testLoginNegative(){
        try {
            test = extent.createTest("Test Negative Login Digital Booking");
            test.log(Status.INFO, "Login Test Started!");
            LoginPage page = new LoginPage();
            page.redirectToLoginPage();
            test.log(Status.PASS, "Open Login Page");
            page.fillLoginUserForm("danytejerina@gmail.com", "daniel");
            test.log(Status.PASS, "Add Login Credentials");
            page.loginUser();
            String res = page.errorMessage();
            Assertions.assertTrue(res.equals("Sus credenciales son inválidas. Por favor, vuelva a intentarlo"));
            test.log(Status.PASS, "Verify Error Message");
            System.out.println("Error Message: " + res);

        } catch (Exception e) {
            test.log(Status.FAIL,"Test Step Fail");
        }
    }

    /**
     * Method to close the driver after the test ends
     */
    @AfterAll
    static void tearDown() {
        extent.flush();
        driver.quit();
    }
}
