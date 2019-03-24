package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillEditedContact(new NewContactData("Edited FirtsName", "Edited Middlename",
            "Edited Lastname", "Edited Nickname", "Edited Title", "Edited Tne Best Company",
            "London", "666", "12456", "55555", "", "qwe666@as.fg",
            "", "", "qwerty.xx.ss", "10", "June",
            "1966", "", "", "", "", "", "", ""));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    }
}
