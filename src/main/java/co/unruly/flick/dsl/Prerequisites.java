package co.unruly.flick.dsl;

import co.unruly.flick.browser.Page;

public interface Prerequisites {
    Prerequisites wasAbleTo(Action action);
    Page hasHappened();
}
