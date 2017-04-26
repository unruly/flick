package co.unruly.flick;

import co.unruly.flick.browser.Page;
import co.unruly.flick.browser.Stage;
import co.unruly.flick.dsl.*;

public interface FlickDSL {

    default Prerequisites given(Stage stage) {
        return new Prerequisites() {
            @Override
            public Prerequisites wasAbleTo(Action action) {
                action.performOn(stage.browser());
                return this;
            }

            @Override
            public Page hasHappened() {
                return stage.currentPage();
            }
        };
    }

    default Actions when(Stage stage) {
        return new Actions() {
            @Override
            public Actions attemptsTo(Action action) {
                action.performOn(stage.browser());
                return this;
            }

            @Override
            public Page enact() {
                return stage.currentPage();
            }
        };
    }

    default Assertions then(Stage stage) {
        return new Assertions() {
            @Override
            public Assertions should(Assertion assertion) {
                assertion.check(stage.browser());
                return this;
            }

            @Override
            public void check() {}
        };
    }
}
