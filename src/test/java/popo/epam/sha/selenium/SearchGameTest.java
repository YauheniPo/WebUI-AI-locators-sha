package popo.epam.sha.selenium;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import popo.epam.sha.selenium.pages.SteamMainPage;
import popo.epam.sha.selenium.pages.SteamSearchPage;

import java.util.List;

public class SearchGameTest extends BaseTest {

    private String gameName;

    @BeforeClass
    @Parameters("game")
    public void beforeClass(@Optional(value = "Dota") String gameName) {
        this.gameName = gameName;
    }

    @Test
    public void testSearchGame() {
        SteamMainPage steamMainPage = new SteamMainPage();
        SteamSearchPage steamSearchPage = steamMainPage.search(gameName);
        List<WebElement> results = steamSearchPage.getResults();

        boolean isResultsContainsGameName = results.stream().findFirst().get().getText().contains(gameName);
        Assert.assertTrue(isResultsContainsGameName, String.format("Searching results have not contained text '%s'", gameName));
    }
}
