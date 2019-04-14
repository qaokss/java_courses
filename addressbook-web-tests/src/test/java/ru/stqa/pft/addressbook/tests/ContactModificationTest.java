package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoAddContactPage();
            app.getContactHelper().createNewContact(new ContactData("TestFirtsName", "Middlename",
                    "Lastname", "Nickname", "adr", "5678", "London@fg.tyh",
                    "", "10", "January", "2000", ""), true);
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Edited FirtsName", "Edited Middlename",
                "Edited Lastname", "Edited Title", "edited adr", "87878787",
                "edited@mail.ee", "", "11", "December", "1988",
                "");
        app.getContactHelper().initContactModification(before.size() - 1);
        app.getContactHelper().fillNewContact(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
