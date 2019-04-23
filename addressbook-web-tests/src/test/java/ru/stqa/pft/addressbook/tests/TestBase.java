package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

    /**
     * Для запуска теста в необходимом браузере нужно в настройках конфигурации в поле VN options указать
     * свойство  -Dbrowser=firefox. Если св-во не указано, то по дефолту будет запускаться в хроме
     */
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME),
            System.getProperty("fileWithProperties", "src/test/resources/local.properties"));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}
