package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().gotoAddContactPage();
    app.getContactHelper().fillNewContact(new NewContactData("TestFirtsName", "Middlename",
            "Lastname", "Nickname", "Title", "Tne Best Company", "London",
            "123456798", "5553535", "", "", "qwe@as.fg",
            "", "", "qwerty.xx.ss", "21", "December",
            "2000", "", "", "", "test1", "", "", ""));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomePage();
  }


}
