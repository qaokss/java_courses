package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillNewContact(NewContactData newContactData) {
        type(By.name("firstname"), newContactData.getFirstname());
        type(By.name("middlename"), newContactData.getMiddlename());
        type(By.name("lastname"), newContactData.getLastname());
        type(By.name("nickname"), newContactData.getNickname());
        type(By.name("title"), newContactData.getTitle());
        type(By.name("company"), newContactData.getNamecompany());
        type(By.name("address"), newContactData.getAddress());
        type(By.name("mobile"), newContactData.getMobilephone());
        type(By.name("email"), newContactData.getEmail1());
        type(By.name("homepage"), newContactData.getHomepage());
        chooseDropDown(By.name("bday"), newContactData.getBirthdayDay());
        chooseDropDown(By.name("bmonth"), newContactData.getBirthdayMonth());
        type(By.name("byear"), newContactData.getBirthdayYear());
        chooseDropDown(By.name("new_group"), newContactData.getGroup());
    }

    public void fillEditedContact(NewContactData newContactData) {
        type(By.name("firstname"), newContactData.getFirstname());
        type(By.name("middlename"), newContactData.getMiddlename());
        type(By.name("lastname"), newContactData.getLastname());
        type(By.name("nickname"), newContactData.getNickname());
        type(By.name("title"), newContactData.getTitle());
        type(By.name("company"), newContactData.getNamecompany());
        type(By.name("address"), newContactData.getAddress());
        type(By.name("mobile"), newContactData.getMobilephone());
        type(By.name("email"), newContactData.getEmail1());
        type(By.name("homepage"), newContactData.getHomepage());
        chooseDropDown(By.name("bday"), newContactData.getBirthdayDay());
        chooseDropDown(By.name("bmonth"), newContactData.getBirthdayMonth());
        type(By.name("byear"), newContactData.getBirthdayYear());
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void initContactModification() {
        click(By.xpath("(//img[@alt='Edit'])[2]"));
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

}
