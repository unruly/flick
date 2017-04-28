package co.unruly.flick;

import co.unruly.flick.browser.Browser;
import co.unruly.flick.browser.Page;
import co.unruly.flick.dsl.Actor;
import co.unruly.flick.dsl.Assertion;
import org.junit.Test;
import org.openqa.selenium.By;

public class PhantomJSTest implements FlickDSL {

    private static class User implements Actor {}

    private final Browser browser = new PhantomJSBrowser();

    @Test
    public void shouldLoadPage() {
        Actor actor = new User();

        Page page = given(actor.using(browser, "http://example.com"))
                .hasHappened();

        then(actor.using(browser, page))
            .should(beAbleToSeeHeader());

    }

    private Assertion beAbleToSeeHeader() {
        return (browser) -> {
            browser.waitUntilEquals(
                () -> browser.findElement(By.xpath("/html/body/div/h1")).getText(),
                "Example Domain"
            );
        };
    }

}