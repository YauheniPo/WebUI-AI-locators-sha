package popo.epam.sha.selenium.pages;

import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@NoArgsConstructor
public class SteamSearchPage extends BasePage {

    private final By searchResultPanelById = By.id("search_results");
    private final By searchResultsByXpath = By.xpath(".//a[contains(@class, 'result')]");

    public List<WebElement> getResults() {
        return findElement(searchResultPanelById).findElements(searchResultsByXpath);
    }
}
