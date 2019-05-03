package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.GroupData;

public class NavigationHelper extends HelperBase {


    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1")) &&  wd.findElement(By.tagName("h1")).getText().equals("Groups") &&
        isElementPresent(By.name("new"))) {
            return;
        }

        click(By.linkText("groups"));
    }

    public void homePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void addContactPage() {
        if (isElementPresent(By.tagName("h1")) && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry") &&
                (! isElementPresent((By.name("new_group"))))) {
                    return;
        }
        click(By.linkText("add new"));
    }

    public void groupFilter(GroupData group) {
        wd.findElement(By.name("group")).click();
        new Select(wd.findElement(By.name("group"))).selectByValue(Integer.toString(group.getId()));    }
}


