package tests;

import appmanager.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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
    protected void chooseCheckbox(By locator) {
        app.wd.findElement(locator).click();
    }

    protected void goTo(String locator) {
        app.wd.findElement(By.linkText(locator)).click();
    }





}
