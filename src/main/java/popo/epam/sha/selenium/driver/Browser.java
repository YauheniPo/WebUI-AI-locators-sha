package popo.epam.sha.selenium.driver;

import com.epam.sha.selenium.SelfHealingDriver;
import com.epam.sha.selenium.config.EngineConfig;
import com.epam.sha.selenium.data.FileSystemPathStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import popo.epam.sha.selenium.BrowserProperties;
import popo.epam.sha.selenium.SmartWait;

import javax.naming.NamingException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Log4j2
public final class Browser {

    private static final BrowserProperties BROWSER_PROPERTIES = BrowserProperties.getInstance();
    private static final String currentBrowser = System.getProperty("browser", BROWSER_PROPERTIES.getBrowser());
    private static ThreadLocal<WebDriver> driverHolder = ThreadLocal.withInitial(Browser::getNewDriver);
    private static EngineConfig engineConfig = EngineConfig.custom()
            .setStorage(new FileSystemPathStorage(Paths.get("sha", "selenium"))).build();
    private static Browser instance = new Browser();

    private Browser() {
        log.info(String.format("Init Browser '%s'", currentBrowser));
    }

    /**
     * Gets thread safe instance of Browser
     *
     * @return browser instance
     */
    public static Browser getInstance() {
        if (instance == null) {
            synchronized (Browser.class) {
                if (instance == null) {
                    instance = new Browser();
                }
            }
        }
        return instance;
    }

    public static WebDriver getDriver() {
        if (driverHolder.get() == null) {
            driverHolder.set(getNewDriver());
        }
        return driverHolder.get();
    }

    public void exit() {
        try {
            getDriver().quit();
            log.info("WebDriver quit");
        } catch (Exception e) {
            log.error(this, e);
        } finally {
            if (isBrowserAlive()) {
                driverHolder.set(null);
            }
        }
    }

    private boolean isBrowserAlive() {
        return driverHolder.get() != null;
    }

    public void openStartPage(final String url) {
        navigate(url);
        windowMaximise();
    }

    public void navigate(final String url) {
        getDriver().navigate().to(url);
    }

    public void windowMaximise() {
        getDriver().manage().window().maximize();
    }

    public void waitForPageToLoad() {
        log.debug("Waiting for page to load");
        ExpectedCondition<Boolean> condition = d ->
                (Boolean) executeJSScript("return document['readyState'] ? 'complete' == document.readyState : true");
        SmartWait.waitUntil(condition, BROWSER_PROPERTIES.getDefaultPageLoadTimeout());
    }

    public Object executeJSScript(final String script) {
        return ((JavascriptExecutor) getDriver()).executeScript(script);
    }

    public Object executeJSScript(final String script, final WebElement element) {
        return ((JavascriptExecutor) getDriver()).executeScript(script, element);
    }

    public void back() {
        getDriver().navigate().back();
        log.info("Return to previous page");
    }

    @AllArgsConstructor()
    public enum BrowserType {
        FIREFOX("firefox"),
        CHROME("chrome"),
        EDGE("edge"),
        IE("ie"),
        DEFAULT("default");

        @Getter
        private final String value;
    }

    private static WebDriver getNewDriver() {
        try {
            RemoteWebDriver driver = BrowserFactory.setUp(currentBrowser);
            driver.manage().timeouts().implicitlyWait(BROWSER_PROPERTIES.getDefaultImplicitlyWait(), TimeUnit.SECONDS);
            log.info("getNewDriver");
            return new SelfHealingDriver(driver, engineConfig);
        } catch (NamingException e) {
            log.error("getNewDriver", e);
        }
        return null;
    }
}
