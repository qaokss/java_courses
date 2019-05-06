package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ActionHelper extends HelperBase {

    public ActionHelper(ApplicationManager app) {
        super(app);
    }


    public void loginAsAdmin() {
        type(By.name("username"), "administrator");
        type(By.name("password"), "root");
        click(By.cssSelector("input.button"));
    }

    public void loginAsUser(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input.button"));
    }

    public String resetPassword() {
        String usernameWithResetedPassword = wd.findElement(By.cssSelector("#edit-username")).getAttribute("value");
        click(By.xpath("//input[@value='Reset Password']"));

        return usernameWithResetedPassword;
    }
}
