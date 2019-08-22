package popo.epam.sha.selenium.driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.naming.NamingException;
import java.util.Arrays;

/**
 * The class-initializer-based browser string parameter.
 */
@Log4j2
final public class BrowserFactory {

    private BrowserFactory() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Setting up Driver
     *
     * @param type BrowserOld type
     * @return RemoteWebDriver
     */
    public static RemoteWebDriver setUp(final Browser.BrowserType type) {
        return getWebDriver(type);
    }

    /**
     * Setting up Driver
     *
     * @param type BrowserOld type
     * @return RemoteWebDriver
     * @throws NamingException NamingException
     */
    public static RemoteWebDriver setUp(final String type) throws NamingException {
        for (Browser.BrowserType browserType : Browser.BrowserType.values()) {
            if (browserType.getValue().equalsIgnoreCase(type)) {
                return setUp(browserType);
            }
        }
        throw new NamingException("Wrong Browser Name: " + Arrays.toString(Browser.BrowserType.values()));
    }

    private static RemoteWebDriver getWebDriver(final Browser.BrowserType type) {
        log.info(String.format("WebDriver %s initialization", type));
        switch (type) {
            case FIREFOX:
                FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                return new FirefoxDriver();
            default:
                log.warn("Default WebDriver");
            case CHROME:
                ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                return new ChromeDriver();
        }
    }
}
