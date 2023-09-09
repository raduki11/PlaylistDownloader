package me.raduserban11.utils;

import me.raduserban11.utils.exceptions.ElementNotFoundInTimeException;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyChromeDriver extends EdgeDriver {

    @Override
    public WebElement findElement(By by) throws ElementNotFoundInTimeException {
        WebElement elementToFind;
        try {
            elementToFind = new WebDriverWait(this, Duration.ofSeconds(10))
                    .until(d -> super.findElement(by));
        } catch (TimeoutException e) {
            throw new ElementNotFoundInTimeException();
        }
        return elementToFind;
    }
}
