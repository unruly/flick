package co.unruly.flick.browser;

public class Stage {
    private final Browser browser;
    private Page page;

    public Stage(Browser browser, Page page) {
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