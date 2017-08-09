package co.unruly.flick;

import co.unruly.flick.browser.Page;
import co.unruly.flick.dsl.Action;
import co.unruly.flick.dsl.Actor;
import co.unruly.flick.dsl.Assertion;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;

public class ExampleTest implements FlickDSL {

    private static class User implements Actor {}

    private final PhantomJSBrowser browser = new PhantomJSBrowser();

    @Test
    public void shouldLoadPage() {
        Actor actor = new User();

        Page page = given(actor.using(browser, "http://example.com"))
                .wasAbleTo(logHelloUsingJS())
                .hasHappened();

        then(actor.using(browser, page))
            .should(beAbleToSeeHeader());
    }

    @Test
    public void shouldNavigateToMoreInfo() {
        Actor actor = new User();

        Page page = given(actor.using(browser, "http://example.com"))
                .wasAbleTo(getMoreInfo())
                .hasHappened();

        then(actor.using(browser, page))
            .should(beOnPage("https://www.iana.org/domains/reserved"));
    }

    @After
    public void tearDown() {
        browser.shutdown();
    }

    private Action getMoreInfo() {
        return (browser) -> browser.findElement(By.xpath("/html/body/div/p[2]/a")).click();
    }

    private Action<PhantomJSBrowser> logHelloUsingJS() {
        return PhantomJSBrowser::hello;
    }

    private Assertion beOnPage(String expectedUrl) {
        return (browser) -> browser.waitUntilEquals(
            () -> browser.getDriver().getCurrentUrl(),
            expectedUrl
        );
    }

    private Assertion beAbleToSeeHeader() {
        return (browser) -> browser.waitUntilEquals(
            () -> browser.findElement(By.xpath("/html/body/div/h1")).getText(),
            "Example Domain"
        );
    }

}