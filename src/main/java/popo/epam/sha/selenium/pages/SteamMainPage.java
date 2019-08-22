package popo.epam.sha.selenium.pages;

import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

@NoArgsConstructor
public class SteamMainPage extends BasePage {

    private By searchInputFieldById = By.id("store_nav_search_term");

    public SteamMainPage setSearchValue(String text) {
        setValue(text, searchInputFieldById);
        return this;
    }

    public SteamSearchPage clickSearch() {
        pressEnter(searchInputFieldById);
        return new SteamSearchPage();
    }

    public SteamSearchPage search(String text) {
        setSearchValue(text);
        return clickSearch();
    }
}
