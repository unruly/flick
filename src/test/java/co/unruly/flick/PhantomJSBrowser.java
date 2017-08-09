package co.unruly.flick;

import co.unruly.flick.browser.Browser;
import co.unruly.flick.browser.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.List;

public class PhantomJSBrowser implements Browser {

    private final WebDriver driver;

    public PhantomJSBrowser() {
        this.driver = new PhantomJSDriver();
    }

    @Override
    public Page load(String url) {
        this.driver.get(url);
        return () -> url;
    }

    @Override
    public WebDriver getDriver() {
        return this.driver;
    }

    @Override
    public WebElement findElement(By by) {
        return this.driver.findElement(by);
    }

    @Override
    public List<WebElement> findElements(By by) {
        return this.driver.findElements(by);
    }

    @Override
    public void shutdown() {
        this.driver.close();
        this.driver.quit();
    }

    public void hello() {
        ((PhantomJSDriver) driver).executePhantomJS("console.log('Hello!');");
    }
}