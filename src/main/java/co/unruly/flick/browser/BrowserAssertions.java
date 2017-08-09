package co.unruly.flick.browser;

import junit.framework.AssertionFailedError;
import org.hamcrest.Matcher;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;

public interface BrowserAssertions {
    WebDriver getDriver();

    default int timeout() { return 5; }

    default void waitUntil(Supplier<Boolean> condition) {
        try {
            new WebDriverWait(getDriver(), timeout()).until((com.google.common.base.Predicate<WebDriver>) webDriver -> {
                return condition.get();
            });
        } catch (TimeoutException e) {
            throw new AssertionFailedError("Timeout waiting");
        }
    }

    default <T> void waitUntilEquals(Supplier<T> elementFinder, T value) {
        try {
            new WebDriverWait(getDriver(), timeout()).until((com.google.common.base.Predicate<WebDriver>) webDriver -> {
                return Objects.equals(elementFinder.get(), value);
            });
        } catch (TimeoutException e) {
            throw new AssertionFailedError("Timeout waiting for " + value);
        }
    }

    default void waitUntilMatches(Supplier<Object> elementFinder, Matcher<String> valueMatcher) {
        try {
            new WebDriverWait(getDriver(), timeout()).until((com.google.common.base.Predicate<WebDriver>) webDriver -> {
                return valueMatcher.matches(elementFinder.get());
            });
        } catch (TimeoutException e) {
            assertThat(elementFinder.get() + "", valueMatcher);
        }
    }
}
