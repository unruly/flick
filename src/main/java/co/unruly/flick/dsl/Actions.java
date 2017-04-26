package co.unruly.flick.dsl;

import co.unruly.flick.browser.Page;

public interface Actions {
    Actions attemptsTo(Action action);
    Page enact();
}
