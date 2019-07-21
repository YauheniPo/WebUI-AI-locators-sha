package popo.epam.sha.selenide;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.sha.selenium.SelfHealingDriver;
import com.epam.sha.selenium.config.EngineConfig;
import com.epam.sha.selenium.data.FileSystemPathStorage;
import popo.epam.sha.selenide.driver.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Paths;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        Browser.getInstance();

        EngineConfig engineConfig = EngineConfig.custom()
            .setStorage(new FileSystemPathStorage(Paths.get("sha", "selenium"))).build();
        WebDriverRunner.setWebDriver(new SelfHealingDriver(Browser.getDriver(), engineConfig));

        Browser.openStartPage();
    }

    @AfterMethod
    public void tearDown() {
        WebDriverRunner.getWebDriver().quit();
    }
}
