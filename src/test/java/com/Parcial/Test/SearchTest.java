package com.Test;

import com.Base.BasePage;
import com.Pages.SearchPage;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.extentReports.ExtentFactory;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * SearchTest class
 */
public class SearchTest {
    private static WebDriver driver;
    static ExtentSparkReporter spark = new ExtentSparkReporter("./target/Spark.html");
    static ExtentReports extent;
    ExtentTest test;

    /**
     * Method to initialize the driver before run the tests
     */
    @BeforeAll
    static void setUp(){
        BasePage _basePage = new BasePage();
        _basePage.openApp();
        driver = _basePage.getDriver();
        extent = ExtentFactory.getInstance();
        extent.attachReporter(spark);
    }

    /**
     * Method to test the input city search functionality
     *
     * Steps:
     * 1. Complete the input whit a city in the list.
     * 2. Click the search button.
     * 3. Check that the results are correct.
     */
    @Test
    @Tag("smoke")
    @Tag("regression")
    @DisplayName("Test Positive Input City Search")
    public void testSearch() throws InterruptedException {
        test = extent.createTest("Test Positive Input City Search");
        test.log(Status.INFO, "Login Test Started!");
        SearchPage page = new SearchPage();
        test.log(Status.PASS, "Open Home Page");
        page.findCity("carilo");
        test.log(Status.PASS, "Find City In Input Field");
        page.searchAction();
        test.log(Status.PASS, "Button Search Pressed");
        String results1 = page.getResults();
        String results2 = page.getLocation();
        assertTrue(results1.contains("Increíble Casa c/ Piscina, WiFi, Bosque y Playa en Carilo"));
        assertTrue(results2.equals("Carilo. MOSTRAR EN EL MAPA"));
        test.log(Status.PASS, "Check Success Search");
    }


    /**
     * Method to close the driver after the test ends
     */
    @AfterAll
    static void tearDown(){
        extent.flush();
        driver.quit();
    }
}
