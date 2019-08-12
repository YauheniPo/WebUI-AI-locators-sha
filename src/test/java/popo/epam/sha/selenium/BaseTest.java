package popo.epam.sha.selenium;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import popo.epam.sha.selenium.driver.Browser;

public class BaseTest {

    private static final StageProperties STAGE_PROPERTIES = StageProperties.getInstance();

    @BeforeMethod()
    public void beforeMethod() {
        Browser.getInstance().openStartPage(STAGE_PROPERTIES.getUrl());
    }

    @AfterMethod
    public void afterMethod() {
        Browser.getInstance().exit();
    }

    protected static WebDriver getWebDriver() {
        return Browser.getDriver();
    }
}
