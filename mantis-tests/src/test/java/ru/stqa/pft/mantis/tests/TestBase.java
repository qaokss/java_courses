package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

    /**
     * для логирования
     */
    Logger logger = LoggerFactory.getLogger(TestBase.class);


    /**
     * Для запуска теста в необходимом браузере нужно в настройках конфигурации в поле VN options указать
     * свойство  -Dbrowser=firefox. Если св-во не указано, то по дефолту будет запускаться в хроме
     * в св-во -DfileWithProperties передаём путь к файлу конфигураций
     */
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME),
            System.getProperty("fileWithProperties", "src/test/resources/local.properties"));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
   //     app.ftp().upload(new File("/src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.back");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
 //       app.ftp().restore("config_inc.php.back", "config_inc.php");
        app.stop();
    }

    public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        return !app.soap().getStatus(issueId).equals("closed");
    }

    /**
     * Метод проверяет статус багрепорта
     * До тех пор, пока баг не исправлен, при вызове этого метода тест будет пропущен
     * @param issueId  id багрепорта
     */
    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }


}
