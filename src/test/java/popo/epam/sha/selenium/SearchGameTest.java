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

    private String game;

    @BeforeClass
    @Parameters("game")
    public void beforeClass(@Optional(value = "Dota") String game) {
        this.game = game;
    }

    @Test
    public void testSearchGame() {
        SteamMainPage steamMainPage = new SteamMainPage();
        SteamSearchPage steamSearchPage = steamMainPage.search(game);
        List<WebElement> results = steamSearchPage.getResults();
        Assert.assertTrue(results.get(0).getText().contains(game), String.format("The first searching result has not text '%s'", game));
    }
}
