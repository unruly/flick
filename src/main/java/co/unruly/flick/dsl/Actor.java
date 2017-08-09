package co.unruly.flick.dsl;


import co.unruly.flick.browser.Browser;
import co.unruly.flick.browser.Page;
import co.unruly.flick.browser.Stage;

public interface Actor {
    default <T extends Browser> Stage<T> using(T browser, Page page) {
        return new Stage<>(browser, page);
    }

    default <T extends Browser> Stage<T> using(T browser, String url) {
        return new Stage<>(browser, browser.load(url));
    }
}
