package popo.epam.sha.selenium.pages;

import popo.epam.sha.selenium.BaseElement;
import popo.epam.sha.selenium.driver.Browser;

public class BasePage extends BaseElement {

    public BasePage() {
        Browser.getInstance().waitForPageToLoad();
    }

}
