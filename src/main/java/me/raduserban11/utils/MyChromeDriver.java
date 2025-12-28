package me.raduserban11.utils;

import me.raduserban11.utils.exceptions.ElementNotFoundInTimeException;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyChromeDriver {

    private final WebDriver driver;

    public MyChromeDriver() {
        System.setProperty(
                "webdriver.chrome.driver",
                "D:/selenium/chromedriver/chromedriver.exe"
        );

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        this.driver = new ChromeDriver(options);
    }

    public WebElement findElement(By by) throws ElementNotFoundInTimeException {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(d -> d.findElement(by));
        } catch (TimeoutException e) {
            throw new ElementNotFoundInTimeException();
        }
    }

    public void get(String url) {
        driver.get(url);
    }

    public void navigateRefresh() {
        driver.navigate().refresh();
    }

    public WebDriver unwrap() {
        return driver;
    }

    public void quit() {
        driver.quit();
    }

    public void closeOtherTabs() {
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        // Switch back so the driver can continue interacting with the page
        driver.switchTo().window(currentHandle);
    }
}
