package popo.epam.sha.selenide.driver;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import popo.epam.sha.selenide.ResourcePropertiesManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Browser {

    private static ResourcePropertiesManager rpStage = new ResourcePropertiesManager("stage.properties");
    private static ResourcePropertiesManager rpBrowser = new ResourcePropertiesManager("browser.properties");
    private static final String BROWSER_URL = rpStage.getStringProperty("url");
    private static DriverManagerType currentBrowser = DriverManagerType.valueOf((System.getenv("browser") == null
            ? rpBrowser.getStringProperty("browser") : ResourcePropertiesManager.getSystemEnvProperty("browser"))
            .toUpperCase(Locale.ENGLISH));
    private static final boolean IS_BROWSER_HEADLESS = rpBrowser.getBooleanProperties("browser.headless");
    public static final long IMPLICITLY_WAIT = rpBrowser.getLongProperties("browser.implicitly.wait");
    static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static volatile Browser instance = null;

    public static void getInstance() {
        Browser localBrowser = instance;
        if (localBrowser == null) {
            synchronized (Browser.class) {
                localBrowser = instance;
                if (localBrowser == null) {
                    instance = new Browser();
                }
            }
        }
    }

    public static void openStartPage() {
        getDriver().manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
        Selenide.open(BROWSER_URL);
    }

    public static WebDriver getDriver() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            return WebDriverRunner.getWebDriver();
        }
        return DriverManager.setUp(currentBrowser);
    }
}
