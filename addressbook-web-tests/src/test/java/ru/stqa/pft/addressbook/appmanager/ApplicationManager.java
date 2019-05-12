package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public WebDriver wd;
    private final Properties properties;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private String browser;
    private String fileWithProperties;
    private DbHelper dbHelper;

    public ApplicationManager(String browser, String fileWithProperties) {
        this.browser = browser;
        properties = new Properties();
        this.fileWithProperties = fileWithProperties;

    }

    public void init() throws IOException {
        properties.load(new FileReader(new File(fileWithProperties)));

        dbHelper = new DbHelper();

        // Если значение настройки selenium.server в .properties не установлено, то используется стандартная инициализация
        if ("".equals(properties.getProperty("selenium.server"))) {

            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();

            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();

            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
            // если мы хотим использовать удалённый сервер, то используем другой тип драйвера
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl")); // берём адресс стенда из файла .properties
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        // берём логин и пароль из файла .properties
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }


    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

}
