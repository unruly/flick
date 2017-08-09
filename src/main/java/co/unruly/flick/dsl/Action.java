package co.unruly.flick.dsl;

import co.unruly.flick.browser.Browser;

public interface Action<T extends Browser> {
    void performOn(T browser);
}
