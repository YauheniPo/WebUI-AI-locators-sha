package popo.epam.sha.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import popo.epam.sha.selenide.pages.SteamMainPage;
import popo.epam.sha.selenide.pages.SteamSearchPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;

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
        ElementsCollection results = steamSearchPage.getResults();
        assertThat(String.format("The first searching result has not text '%s'", game),
                results.first().has(Condition.text(game)));
    }
}
