package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {


    public NavigationHelper(ApplicationManager app) {
        super(app);
    }


    public void loginPage() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    }

    public void manageMenu() {
        wd.findElement(By.linkText("Manage")).click();
    }
    public void manageUsersPage() {
        manageMenu();
        wd.findElement(By.linkText("Manage Users")).click();
    }

    public void editUserPage() {
        manageMenu();
        manageUsersPage();
        wd.findElement(By.xpath("//a[contains(@href, 'manage_user_edit_page.php?user_id=')]")).click();
    }

}


