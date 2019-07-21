package popo.epam.sha.selenide.driver;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

@Log4j2
public final class DriverManager {

    public static WebDriver setUp(DriverManagerType browserType) {
        WebDriverManager.getInstance(browserType).setup();
        switch (browserType) {
            case CHROME:
                return new ChromeDriver();
            case FIREFOX:
                return new FirefoxDriver();
            case EDGE:
                return new EdgeDriver();
            case IEXPLORER:
                return new InternetExplorerDriver();
            default:
                log.info(String.format("Init '%s' default browser", browserType));
        }
        log.info(String.format("Set up '%s' browser", CHROME));
        return new ChromeDriver();
    }
}
