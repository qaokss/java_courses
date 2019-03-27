package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
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
