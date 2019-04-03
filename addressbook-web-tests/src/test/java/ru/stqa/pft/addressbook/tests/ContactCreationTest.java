package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().gotoAddContactPage();
    app.getContactHelper().fillNewContact(new ContactData("TestFirtsName", "Middlename",
            "Lastname", "Nickname", "Title", "Tne Best Company", "London",
            "123456798", "5553535", "", "", "qwe@as.fg",
            "", "", "qwerty.xx.ss", "21", "December",
            "2000", "", "", "", "test1", "", "", ""), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomePage();
  }

}
