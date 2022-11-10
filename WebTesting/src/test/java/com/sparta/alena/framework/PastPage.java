package com.sparta.alena.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;


public class PastPage {
    private WebDriver webDriver;
    private By pageTop = new By.ByClassName("pagetop");

    public PastPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public boolean isDateYesterday() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        String menuBar = webDriver.findElement(pageTop).getText();
        return menuBar.contains(yesterday.toString());
    }
}


