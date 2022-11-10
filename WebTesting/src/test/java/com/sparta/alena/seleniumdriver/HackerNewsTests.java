package com.sparta.alena.seleniumdriver;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.StringContains.containsString;

public class HackerNewsTests {

    private static WebDriver webDriver;


    @BeforeAll
    public static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
//        chromeOptions.addArguments("headless");
        webDriver = new ChromeDriver(chromeOptions);
    }

    @BeforeEach
    public void setup() {
        webDriver.manage().deleteAllCookies();
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

    @Test
    @DisplayName("Test1")
    public void checkPastLinkASK(){
        WebElement pastLink = webDriver.findElement(By.linkText("ask"));
        pastLink.click();
        Assertions.assertEquals("https://news.ycombinator.com/ask", webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Find the passowrd box")
    public void relativeElement(){
        webDriver.get("https://news.ycombinator.com/login?qoto=news");
        WebElement userNameBox = webDriver.findElement(By.name("acct"));
        WebElement passwordInput = webDriver.findElement(
                RelativeLocator.with(By.tagName("input")).below(userNameBox));
        passwordInput.sendKeys("password");
        Assertions.assertEquals("password", passwordInput.getAttribute("value"));
    }

    @Test
    @DisplayName("Check that the past page has yesterday's date")
    void checkYesterdayDay() {
        WebElement pastLink = webDriver.findElement(By.linkText("past"));
        pastLink.click();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        String topString = webDriver.findElement(By.className("pagetop")).getText();
        System.out.println(topString);
        MatcherAssert.assertThat(topString, containsString(yesterday.toString()));
    }

    @Test
    @DisplayName("Check login")
    public void checkLogin(){
        webDriver.get("https://news.ycombinator.com/submit");
        WebElement forgotYourPassword = webDriver.findElement(By.linkText("Forgot your password?"));
        forgotYourPassword.click();
        String topString = webDriver.findElement(By.tagName("b")).getText();
        System.out.println(topString);
        Assertions.assertEquals("Rest your password", topString);
    }

    @Test
    @DisplayName("Elements list")
    public void testElementList() {
        webDriver.get("https://news.ycombinator.com/jobs");
       WebElement elementName = webDriver.findElement(By.linkText("jobs"));
        elementName.click();
        Assertions.assertEquals("https://news.ycombinator.com/jobs", webDriver.getCurrentUrl());
        }

    @Test
    void printsTableDataElements() {
        List<WebElement> elements = webDriver.findElements(By.cssSelector("td"));
        for (WebElement row : elements) {
            System.out.println(row);
        }
        Assertions.assertEquals(159, elements.size());
    }

    @Test
    @DisplayName("Listing jobs")
    public void loopThroughJobs() {
        webDriver.get("https://news.ycombinator.com/jobs");
        List<WebElement> jobs = webDriver.findElements(By.cssSelector("#td .software"));
        List<WebElement> jb = new ArrayList<>();
        for (WebElement jobsElement : jobs) {
            if (jobsElement.toString().contains("software")) {
                jb.add(jobsElement);
            }
            System.out.println(jb);
        }
        Assertions.assertEquals(280, jb.size());
    }

    @Test
    @DisplayName("Using Explicit wait")
    public void usingExplicitWait() {
        //Fine grained waiting for a given element to have a state
        webDriver.get("https://duckduckgo.com");
        // search for pizza
        webDriver.findElement(By.id("search_form_input_homepage")).sendKeys("pizza" + Keys.ENTER);
        // create a WebDriverWait object
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement firstResult = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className("place-list-item")));
        System.out.println(firstResult.getText());
        Assertions.assertTrue(firstResult.getText().contains("1. "));
    }

//    @Test
//    @DisplayName("Search test")
//    public void searchKey(){
//        webDriver.findElement(By.name("q")).sendKeys("Java", Keys.ENTER);
//        Assertions.assertEquals();
//
//    }
    @AfterEach
    public void tearDown() {
//        webDriver.close();
//        webDriver.quit();
    }


    @AfterAll
    public static void teardownAll() {
        if (webDriver != null)
            webDriver.quit();
        }
}
