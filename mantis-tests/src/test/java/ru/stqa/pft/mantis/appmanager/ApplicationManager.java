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

    public WebDriver wd;
    private final Properties properties;
    private String browser;
    private String fileWithProperties;


    public ApplicationManager(String browser, String fileWithProperties) {
        this.browser = browser;
        properties = new Properties();
        this.fileWithProperties = fileWithProperties;

    }

    public void init() throws IOException {
        properties.load(new FileReader(new File(fileWithProperties)));



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


    public void stop() {
        wd.quit();
    }



}
