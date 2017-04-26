package co.unruly.flick.browser;

import org.openqa.selenium.WebElement;

public interface PageElement {
    default String value(Browser browser) {
        return element(browser).getText();
    }

    WebElement element(Browser browser);
}
