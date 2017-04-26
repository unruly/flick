package co.unruly.flick.dsl;

import co.unruly.flick.browser.Browser;

public interface Action {
    void performOn(Browser browser);
}
