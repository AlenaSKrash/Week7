package com.sparta.alena.framework.pom;

import com.sparta.alena.framework.PastPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.file.Watchable;

public class HomePage {
    private final WebDriver webDriver;
    private By commentsLink = new By.ByLinkText("comments");
    private By pastLink = new By.ByLinkText("past");
    private By showLink = new By.ByLinkText("show");

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        goToHomePage();
    }

    public CommentsPage goToCommentsPage(){
        webDriver.findElement(commentsLink).click();
        return new CommentsPage(webDriver);
    }

    private void goToHomePage() {
        webDriver.get("https://news.ycombinator.com/news");
    }

    public PastPage goToPastPage() {
        webDriver.findElement(pastLink).click();
        return new PastPage(webDriver);
    }

    public ShowPage gotoShowPage(){
        webDriver.findElement(showLink).click();
        return new ShowPage(webDriver);
    }
}