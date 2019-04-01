package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoAddContactPage();
            app.getContactHelper().createNewContact(new ContactData("TestFirtsName", "Middlename",
                    "Lastname", "Nickname", "Title", "Tne Best Company", "London",
                    "123456798", "5553535", "", "", "qwe@as.fg",
                    "", "", "qwerty.xx.ss", "21", "December",
                    "2000", "", "", "", "test1", "",
                    "", ""), true);
            app.getNavigationHelper().gotoHomePage();
        }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillNewContact(new ContactData("Edited FirtsName", "Edited Middlename",
            "Edited Lastname", "Edited Nickname", "Edited Title", "Edited Tne Best Company",
            "London", "666", "12456", "55555", "", "qwe666@as.fg",
            "", "", "qwerty.xx.ss", "10", "June",
            "1966", "", "", "", null, "", "", ""),
            false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    }
}
