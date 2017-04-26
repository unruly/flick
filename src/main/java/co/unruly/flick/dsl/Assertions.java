package co.unruly.flick.dsl;

public interface Assertions {
    Assertions should(Assertion assertion);
    void check();
}
