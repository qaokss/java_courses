package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        app.goTo().homePage();
        // если на странице нет ни одного созданного контакта - создаём его
        if (! app.getContactHelper().isThereAContact()) {
            app.goTo().addContactPage();
            app.getContactHelper().createNewContact(new ContactData("TestFirtsName", "Middlename",
                    "Lastname", "Nickname", "adr", "5678", "London@fg.tyh",
                    "", "10", "January", "2000", "000"), true);
            app.goTo().homePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().chooseCheckboxContact(before.size() - 1);
        app.getContactHelper().initContactDeletion();
        app.getContactHelper().acceptAlert();
        app.goTo().homePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);


    }
}
