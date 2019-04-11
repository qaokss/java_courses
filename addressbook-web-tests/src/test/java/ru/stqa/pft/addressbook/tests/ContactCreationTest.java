package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {

        app.getNavigationHelper().gotoAddContactPage();
        app.getContactHelper().fillNewContact(new ContactData(999, "TestFirtsName", "Middlename",
                "Lastname", "Nickname", "adr", "5678", "London@fg.tyh",
                "", "10", "January", "2000", "000"), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
    }

}
