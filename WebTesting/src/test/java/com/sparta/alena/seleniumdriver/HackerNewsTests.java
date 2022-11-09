package com.sparta.alena.seleniumdriver;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

public class HackerNewsTests {

    private static WebDriver webDriver;

    @BeforeAll
    public static void setAll(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    }

    @BeforeEach
    public void setUp() {
//        webDriver.get("https://news.ycombinator.com/");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
        chromeOptions.addArguments("headless");
        webDriver = new ChromeDriver();
        webDriver.get("https://news.ycombinator.com/");

    }

    @Test
    @DisplayName("U")
    public void checkDriver() {
        Assertions.assertEquals("https://news.ycombinator.com/", webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Test")
    public void checkPastLink(){
        WebElement pastLink = webDriver.findElement(By.linkText("past"));
        pastLink.click();
        Assertions.assertEquals("https://news.ycombinator.com/front", webDriver.getCurrentUrl());
    }


    @AfterEach
    public void tearDown() {
        webDriver.close();
//        webDriver.quit();
    }


    @AfterAll
    public static void teardownAll() {
        if (webDriver != null)
            webDriver.quit();
        }
}
