package com.sparta.alena.framework.pom;

import org.openqa.selenium.WebDriver;

public class CommentsPage {
    private WebDriver webDriver;

    public CommentsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public String getUrl(){
        return webDriver.getCurrentUrl();
    }

}
