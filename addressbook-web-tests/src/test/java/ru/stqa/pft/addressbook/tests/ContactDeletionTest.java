package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().chooseCheckboxContact();
    app.getContactHelper().initContactDeletion();
    app.getContactHelper().acceptAlert();
    }
}
