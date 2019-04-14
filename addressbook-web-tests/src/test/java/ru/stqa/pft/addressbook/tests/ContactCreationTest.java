package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoAddContactPage();
        ContactData contact = new ContactData("TestFirtsName", "Middlename",
                "Lastname", "Tittle", "adr", "5678", "London@fg.tyh",
                "", "10", "January", "2000", "000");
        app.getContactHelper().createNewContact(contact, true);
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);


        // находим и подставляем максимальный id в список с помощью лямбда-выражения
        contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
        before.add(contact);

        // сравниваем отсортированные списки
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);


    }

}
