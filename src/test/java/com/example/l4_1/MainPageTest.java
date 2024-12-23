package com.example.l4_1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        By searchFieldCss = By.cssSelector("#sb_form_q");
        String input = "Google";
        WebElement searchField = driver.findElement(searchFieldCss);
        searchField.sendKeys(input);
        searchField.submit();
        WebElement searchPageField = driver.findElement(searchFieldCss);
        assertEquals(input, searchPageField.getAttribute("value"));
    }

    @Test
    public void seleniumCheck() {
        String address = ("https://www.selenium.dev/");
        WebElement searchField = driver.findElement(By.cssSelector("#sb_form_q"));
        searchField.sendKeys("Selenium");
        searchField.submit();
        List<WebElement> results = driver.findElements(By.xpath("//a[contains(@class ,'tilk')]"));
        helpSelenium(results, 0);
        List<String> tabs_windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(tabs_windows.size() - 1));
        String check = driver.getCurrentUrl();
        assertEquals(address, check, "URL не совпадает");
    }

    public void helpSelenium(List<WebElement> results, int num) {
        results.get(num).click();
    }
}

