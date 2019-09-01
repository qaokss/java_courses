package tests;

import appmanager.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;

public class TestBase {
    protected final ApplicationManager app = new ApplicationManager();

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }


    protected void click(By locator) {
        app.wd.findElement(locator).click();
    }


    protected void goTo(String locator) {
        app.wd.findElement(By.linkText(locator)).click();
    }

    /**
     * Переключение во вторую вкладку браузера
     */
    protected void switchToNewTab() {
        // ожидание, пока не загрузится вторая вкладка
        Wait<WebDriver> wait = new WebDriverWait(app.wd, 5, 1000);
        wait.until(webDriver -> webDriver.getWindowHandles().size() > 1);

        ArrayList<String> tabs = new ArrayList<String>(app.wd.getWindowHandles());
        app.wd.switchTo().window(tabs.get(1));
    }

    /**
     * Получение текущего URL страницы
     */
    protected String currentLink() {
        return app.wd.getCurrentUrl();
    }


}
