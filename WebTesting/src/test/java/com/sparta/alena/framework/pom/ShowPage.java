package com.sparta.alena.framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShowPage {
    private WebDriver webDriver;

    public ShowPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }
}
