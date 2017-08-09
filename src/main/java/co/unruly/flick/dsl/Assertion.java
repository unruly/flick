package co.unruly.flick.dsl;

import co.unruly.flick.browser.Browser;

public interface Assertion<T extends Browser> {
    void check(T browser);
}
