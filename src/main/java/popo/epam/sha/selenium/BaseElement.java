package popo.epam.sha.selenium;

import com.epam.sha.selenium.PageAwareBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import popo.epam.sha.selenium.driver.Browser;

public class BaseElement {

    protected PageAwareBy fetchBy(By locator) {
        return PageAwareBy.by(this.getClass().getSimpleName(), locator);
    }

    protected void setValue(String text, By locator) {
        findElement(fetchBy(locator)).sendKeys(text);
    }

    protected void pressEnter(By locator) {
        findElement(fetchBy(locator)).sendKeys(Keys.ENTER);
    }

    protected WebElement findElement(By locator) {
        return getDriver().findElement(locator);
    }

    private WebDriver getDriver() {
        return Browser.getDriver();
    }
}
