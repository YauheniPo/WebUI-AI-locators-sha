package popo.epam.sha.selenium.utils;

import lombok.Getter;
import popo.epam.sha.selenium.driver.Browser;
import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

import java.util.concurrent.TimeUnit;

@Resource.Classpath("browser.properties")
public class BrowserProperties {

    @Getter
    @Property("browser")
    private String browser = Browser.BrowserType.CHROME.getValue();

    @Getter
    @Property("browser.defaultConditionTimeout")
    private int defaultConditionTimeoutMillis = 30000;

    @Getter
    @Property("browser.defaultImplicitlyWait")
    private long defaultImplicitlyWait = 4;

    @Getter
    @Property("browser.defaultPageLoadTimeout")
    private long defaultPageLoadTimeout = 30;

    @Getter
    @Property("browser.defaultPollingInterval")
    private long defaultPollingInterval = 150;

    private static BrowserProperties configuration = new BrowserProperties();

    private BrowserProperties() {
        PropertyLoader.populate(this);
    }

    public static BrowserProperties getInstance() {
        if (configuration == null) {
            synchronized (BrowserProperties.class) {
                if (configuration == null) {
                    configuration = new BrowserProperties();
                }
            }
        }
        return configuration;
    }

    public int getDefaultConditionTimeoutSeconds() {
        return (int) TimeUnit.MILLISECONDS.toSeconds(defaultConditionTimeoutMillis);
    }
}