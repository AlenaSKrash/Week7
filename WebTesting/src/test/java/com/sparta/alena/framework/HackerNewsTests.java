package com.sparta.alena.framework;

import com.sparta.alena.framework.pom.HomePage;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Watchable;

import static org.hamcrest.core.IsEqual.equalTo;

public class HackerNewsTests {
    private static WebDriver webDriver;
    private HomePage homePage;

    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";

    @BeforeAll
    public static void setupAll() {
        System.setProperty("webdriver.chrome.driver", DRIVER_LOCATION);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
        chromeOptions.addArguments("headless");
        webDriver = new ChromeDriver(chromeOptions);
    }

    @BeforeEach
    public void setup() {
        webDriver.manage().deleteAllCookies();
        homePage = new HomePage(webDriver);
    }

    @Test
    @DisplayName("Testing the comments link")
    public void testCommentsLink ()
    {
        var commentsPage = homePage.goToCommentsPage();
        var result = commentsPage.getUrl();
        Assertions.assertEquals("https://news.ycombinator.com/newcomments",
                homePage.goToCommentsPage().getUrl());
    }

    @Test
    @DisplayName("Testing the comments link using")
    public void testCommentsLink_UsingHamcrest ()
    {
        MatcherAssert.assertThat(homePage.goToCommentsPage().getUrl(), equalTo("https://news.ycombinator.com/newcomments"));
    }

    @Test
    @DisplayName("Testing the date on the past page is yesterday")
    public void testPastDate()
    {
        var pastPage = homePage.goToPastPage();
        Assertions.assertTrue(pastPage.isDateYesterday());
    }

    @Test
    @DisplayName("Testing ShowPage link")
    public void testShowPageLink(){
        MatcherAssert.assertThat(homePage.gotoShowPage().getUrl(), equalTo("https://news.ycombinator.com/show"));
    }

    @AfterAll
    public static void afterAll() {
        webDriver.close();
        webDriver.quit();
    }
}