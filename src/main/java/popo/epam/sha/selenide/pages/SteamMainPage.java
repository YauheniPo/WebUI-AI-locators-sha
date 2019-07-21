package popo.epam.sha.selenide.pages;

import com.epam.sha.selenium.PageAwareBy;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SteamMainPage extends BasePage {

    private final String searchInputFieldId = "store_nav_search_term";

    public SteamMainPage setSearchValue(String text) {
        PageAwareBy by = PageAwareBy.by("main", By.id(searchInputFieldId));
        $(by).setValue(text);
        return this;
    }

    public SteamSearchPage clickSearch() {
        PageAwareBy by = PageAwareBy.by("main", By.id(searchInputFieldId));
        $(by).pressEnter();
        return new SteamSearchPage();
    }

    public SteamSearchPage search(String text) {
        setSearchValue(text);
        return clickSearch();
    }
}
