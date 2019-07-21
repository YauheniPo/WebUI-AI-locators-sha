package popo.epam.sha.selenide.pages;

import com.codeborne.selenide.ElementsCollection;
import com.epam.sha.selenium.PageAwareBy;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SteamSearchPage extends BasePage {

    private final String searchResultPanelId = "search_results";

    public ElementsCollection getResults() {
        PageAwareBy by = PageAwareBy.by("search", By.id(searchResultPanelId));
        return $(by).findAll(byXpath(".//a[contains(@class, 'result')]"));
    }
}
