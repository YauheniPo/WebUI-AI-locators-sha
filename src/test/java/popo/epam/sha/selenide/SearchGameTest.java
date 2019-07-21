package popo.epam.sha.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import popo.epam.sha.selenide.pages.SteamMainPage;
import popo.epam.sha.selenide.pages.SteamSearchPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;


public class SearchGameTest extends BaseTest {

    @Test
    public void testSearchGame() {
        final String dotaGame = "Dota";

        SteamMainPage steamMainPage = new SteamMainPage();
        SteamSearchPage steamSearchPage = steamMainPage.search(dotaGame);
        ElementsCollection results = steamSearchPage.getResults();
        assertThat(String.format("The first searching result has not text '%s'", dotaGame),
                results.first().has(Condition.text(dotaGame)));
    }
}
