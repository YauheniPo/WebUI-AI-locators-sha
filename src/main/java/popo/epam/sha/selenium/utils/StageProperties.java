package popo.epam.sha.selenium.utils;

import lombok.Getter;
import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

@Resource.Classpath("stage.properties")
public class StageProperties {

    @Getter
    @Property("stage.url")
    private String url;

    private static StageProperties configuration = new StageProperties();

    public static StageProperties getInstance() {
        if (configuration == null) {
            synchronized (StageProperties.class) {
                if (configuration == null) {
                    configuration = new StageProperties();
                }
            }
        }
        return configuration;
    }

    private StageProperties() {
        PropertyLoader.populate(this);
    }
}