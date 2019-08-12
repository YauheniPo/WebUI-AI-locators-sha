package popo.epam.sha.selenium;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import popo.epam.sha.selenium.driver.Browser;

import java.util.concurrent.TimeUnit;

@Log4j2
final public class SmartWait {

    public static final BrowserProperties BROWSER_PROPERTIES = BrowserProperties.getInstance();

    private SmartWait() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> T waitFor(ExpectedCondition<T> condition) {
        return waitFor(condition, BROWSER_PROPERTIES.getDefaultConditionTimeoutSeconds());
    }

    private static <T> T waitFor(ExpectedCondition<T> condition, long timeOutInSeconds) {
        try {
            return wait(timeOutInSeconds, BROWSER_PROPERTIES.getDefaultPollingInterval()).until(condition);
        } finally {
            Browser.getDriver().manage().timeouts().implicitlyWait(BROWSER_PROPERTIES.getDefaultImplicitlyWait(), TimeUnit.SECONDS);
        }
    }

    public static void waitUntil(ExpectedCondition<Boolean> condition, long timeOutInSeconds) {
        wait(timeOutInSeconds, BROWSER_PROPERTIES.getDefaultPollingInterval()).until(condition);
    }

    public static void waitUntil(ExpectedCondition<Boolean> condition) {
        waitUntil(condition, BROWSER_PROPERTIES.getDefaultConditionTimeoutSeconds());
    }

    private static Wait<WebDriver> wait(long timeOutInSeconds, long pollingInterval) {
        Browser.getDriver().manage().timeouts().implicitlyWait(0L, TimeUnit.MILLISECONDS);
        return new WebDriverWait(Browser.getDriver(), timeOutInSeconds, pollingInterval)
                .ignoring(StaleElementReferenceException.class, WebDriverException.class);
    }

    public static boolean waitForTrue(ExpectedCondition<Boolean> condition) {
        return waitForTrue(condition, BROWSER_PROPERTIES.getDefaultConditionTimeoutMillis());
    }

    public static boolean waitForTrue(ExpectedCondition<Boolean> condition, long timeOutInSeconds) {
        try {
            return waitFor(condition, timeOutInSeconds);
        } catch (Exception e) {
            log.debug("waitForTrue", e);
            return false;
        }
    }

    public static boolean waitForFalse(ExpectedCondition<Boolean> condition, long timeOutInSeconds) {
        try {
            return !waitFor(condition, timeOutInSeconds);
        } catch (Exception e) {
            log.debug("waitForFalse", e);
            return true;
        }
    }
}