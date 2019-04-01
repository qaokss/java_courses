package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoAddContactPage();
            app.getContactHelper().createNewContact(new ContactData("TestFirtsName", "Middlename",
                    "Lastname", "Nickname", "Title", "Tne Best Company", "London",
                    "123456798", "5553535", "", "", "qwe@as.fg",
                    "", "", "qwerty.xx.ss", "21", "December",
                    "2000", "", "", "", "test1", "",
                    "", ""), true);
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().chooseCheckboxContact();
        app.getContactHelper().initContactDeletion();
        app.getContactHelper().acceptAlert();
    }
}
