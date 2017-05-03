# flick

[![Build Status](https://travis-ci.org/unruly/flick.svg?branch=master)](https://travis-ci.org/unruly/flick)
[![Release Version](https://img.shields.io/maven-central/v/co.unruly/flick.svg)](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22co.unruly%22%20AND%20a%3A%22flick%22)

Flick is an acceptance testing library for Java that implements the Screenplay Pattern, an improvement over the Page Object pattern for UI testing.

At Unruly, we use this to drive our [monitoring-in-production](https://vimeo.com/channels/pipelineconf/123629954) specs against our customer-facing Reporting UI.

## Getting started with Flick

Install from Maven Central

```xml
<dependency>
  <groupId>co.unruly</groupId>
  <artifactId>flick</artifactId>
  <version>1.0.1</version>
</dependency>
```

## Using Flick in your tests

A test that implements the FlickDSL interface has access to the given/when/then methods to build a coherent and clean test - these are _default_ methods so classes need not override them. FlickDSL is actually more like a mixin.

```java
public class ExampleTest implements FlickDSL {
   // Tests go here
}
```

## Screenplay Pattern

Flick abstracts away notions of WebDriver/UI specific code from the test. You can find more detail on the Screenplay Pattern [here](https://ideas.riverglide.com/page-objects-refactored-12ec3541990)

### Example Test

```java
@Test
public void shouldRestrictToDateRange() {
  InternalUser actor = new InternalUser();  // Actor

  final Page page = given(actor.using(browser, "acme-reporting-ui.com"))
    .wasAbleTo(loginWith(actor.user(), actor.password())) // Logging in as an Action
    .hasHappened();

  when(actor.using(browser, page))
    .attemptsTo(generateReport()) // Generating a report as Action
    .enact();

  then(actor.using(browser, page))
    .should(haveTotalWithValue("LOTS"))) // Inspecting the output as an Assertion
    .check();
}
```

## Why would I use Flick/the Screenplay pattern?

The Screenplay pattern guides us towards higher-level abstractions and decoupling from implementation - by encapsulating interactions and validations within interfaces that only expose the Browser.

The implementation can then be refactored without changing the tests outside of swapping in new actions/assertions, leading to less brittle, more robust tests.

## Concepts in Flick

A test consists of an *Actor* interacting with a *Stage* - we phrase this as *Given* (an Action is performed), *When* (an Action is performed), *Then* (an Assertion must be valid).

### Actions

An [Action](./src/main/java/co/unruly/flick/dsl/Action.java) is a function that takes a Browser (to interact with) and returns nothing.

For example
```java
class NavigateToPage implements Action {

  private final String url;

  private NavigateToPage(String url) { this.url = url; }

  @Override
  public void performOn(Browser browser) {
    browser.load(url);
  }
}
```

In a more functional style:

```java
public static Action navigateToPage(final String url) {
  return browser -> browser.load(url);
}
```

### Assertions

An [Assertion](./src/main/java/co/unruly/flick/dsl/Assertion.java) is a function that takes a Browser (to interact with) and returns nothing.

```java
public class HasHeader implements Assertion {
    @Override
    public void check(Browser browser) {
        browser.waitUntil(browser.findElement(By.xpath("//*h1"))::isPresent);
    }
}
```

In a more functional style:

```java
public static Assertion hasHeader() {
  return browser -> browser.waitUntil(browser.findElement(By.xpath("//*h1"))::isPresent);
}
```
