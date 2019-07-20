## How to start

### Add 'sha-1.2.0-SNAPSHOT' dependency to your pom.xml
```
<dependency>
    <groupId>com.epam.sha</groupId>
    <artifactId>selenium</artifactId>
    <version>1.2.0-SNAPSHOT</version>
</dependency>
```

### To enable dependencies resolving add artifactory server in your Maven settings file.
This file usually could be found in .m2 folder of your home directory (e.g. C:\Users\FirstName_LastName\.m2\)
If file settings.xml add following block of XML:

```
<server>
    <username>FirstName_LastName@epam.com</username>
    <password>YOUR_PASSWORD<password>
    <id>epam-snapshots</id>
</server>
```

Then, add repository in your pom.xml file:
```
<repositories>
    <repository>
        <id>epam-snapshots</id>
        <url>https://artifactory.epam.com/artifactory/libs-snapshots-local</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```

### 1. Driver initialization
### 1.1 Declare custom EngineConfig with the path where to store new locators. For example 'sha\\selenium'
``` EngineConfig engineConfig = EngineConfig
               .custom()
               .setStorage(new FileSystemPathStorage(Paths.get("sha", "selenium")))
               .build();```

### 1.1.1 Or Declare default EngineConfig. Default path to new locators storage will be 'target\\selenium'
``` EngineConfig engineConfig = EngineConfig.build();```

* Important! Do not delete data from the folder where files with new locators are stored. They are used to perform self-healing in next automation runs*

### 1.2 Specify Selenide to use instance of SelfHealingDriver
```WebDriverRunner.setWebDriver(new SelfHealingDriver(new ChromeDriver(), engineConfig));```

### 2. Locating elements
### 2.1 Using PageAwareBy.by to locate your elements
```By buttonBy = PageAwareBy.by("MainPage", By.id(testButtonId));```

* where the first argument "MainPage" is the name of the page to which the WebElement belongs.

Then you can simply find element
```$(buttonBy).click(); ```

Or use the shorter form
```$(PageAwareBy.by("MainPage", By.id(testButtonId))).click();```

### 2.2 Using @PageAwareFindBy instead of @FindBy
```@PageAwareFindBy(page="MainPage", findBy = @FindBy(id = "markup-generation-button"))
    SelenideElement testButtonId;```

or not declaring the page (not recommended). In this case page name will be set by default with the class name in which the locator is declared.

 ```@PageAwareFindBy(findBy = @FindBy(id = "markup-generation-button"))
    SelenideElement testButtonId;```

#### To refactor your project fast you could use Idea hotkeys cmd+shift+r and perform replacement