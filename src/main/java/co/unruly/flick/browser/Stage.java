package co.unruly.flick.browser;

public class Stage<T extends Browser> {
    private final T browser;
    private Page page;

    public Stage(T browser, Page page) {
        this.browser = browser;
        this.page = page;
    }

    public Page currentPage() {
        return page;
    }

    public Browser browser() {
        return this.browser;
    }
}