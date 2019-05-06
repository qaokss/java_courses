package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private WebDriver wd;
    private final Properties properties;
    private String browser;
    private String fileWithProperties;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private NavigationHelper navigationHelper;
    private ActionHelper actionHelper;


    public ApplicationManager(String browser, String fileWithProperties) {
        this.browser = browser;
        properties = new Properties();
        this.fileWithProperties = fileWithProperties;

    }

    public void init() throws IOException {
        properties.load(new FileReader(new File(fileWithProperties)));


    }


    public void stop() {
        if (wd != null) {
            wd.quit();

        }
    }


    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }


    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }



    public FtpHelper ftp() {
        if (ftp == null) {
            ftp =  new FtpHelper(this);
        }
        return ftp;
    }

    /**
     * Используется шаблон проектирования "ленивая инициализация"
     * Драйвер браузера будет инициализирован (вызван) только тогда, когда к нему кто-то обратится
     */
    public WebDriver getDriver() {
        if (wd == null) {

            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();

            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();

            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }

            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl")); // берём адресс стенда из файла .properties

        }
        return wd;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james() {
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public NavigationHelper goTo() {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;    }

    public ActionHelper doAction() {
        if (actionHelper == null) {
            actionHelper = new ActionHelper(this);
        }
        return actionHelper;    }

}
