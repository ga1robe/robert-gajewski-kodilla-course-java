package com.kodilla.testing2.google;

import com.kodilla.testing2.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class GoogleTestingApp {
    public static final String BUTTONID = "L2AGLb";
    public static final String SEARCHFIELD = "q";


    public static void main(String[] args) throws MalformedURLException {
        WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://google.com");
        WebElement searchButton = driver.findElement(By.id(BUTTONID));
        searchButton.click();

        WebElement searchField = driver.findElement(By.name(SEARCHFIELD));

        searchField.sendKeys("Kodilla");
        searchField.submit();

    }
}
