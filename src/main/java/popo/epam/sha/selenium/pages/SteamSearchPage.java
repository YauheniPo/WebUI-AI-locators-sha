package popo.epam.sha.selenium.pages;

import com.epam.sha.selenium.PageAwareBy;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import popo.epam.sha.selenium.driver.Browser;

import java.util.List;

@NoArgsConstructor
public class SteamSearchPage extends BasePage {

    private final String searchResultPanelId = "search_results";

    public List<WebElement> getResults() {
        PageAwareBy by = PageAwareBy.by("search", By.id(searchResultPanelId));
        return Browser.getDriver().findElement(by).findElements(By.xpath(".//a[contains(@class, 'result')]"));
    }
}
