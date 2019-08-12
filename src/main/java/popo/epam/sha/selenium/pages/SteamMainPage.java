package popo.epam.sha.selenium.pages;

import com.epam.sha.selenium.PageAwareBy;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import popo.epam.sha.selenium.driver.Browser;

@NoArgsConstructor
public class SteamMainPage extends BasePage {

    private String searchInputFieldId = "store_nav_search_term";

    public SteamMainPage setSearchValue(String text) {
        PageAwareBy by = PageAwareBy.by("main", By.id(searchInputFieldId));
        Browser.getDriver().findElement(by).sendKeys(text);
        return this;
    }

    public SteamSearchPage clickSearch() {
        PageAwareBy by = PageAwareBy.by("main", By.id(searchInputFieldId));
        Browser.getDriver().findElement(by).sendKeys(Keys.ENTER);
        return new SteamSearchPage();
    }

    public SteamSearchPage search(String text) {
        setSearchValue(text);
        return clickSearch();
    }
}
