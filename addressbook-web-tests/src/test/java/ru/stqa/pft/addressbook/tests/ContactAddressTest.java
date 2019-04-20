package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {

    @BeforeMethod
    public void EnsurePreconditions() {
        app.goTo().homePage();
        // если на странице нет ни одного созданного контакта - создаём его
        if (app.contact().allContacts().size() == 0) {
            app.goTo().addContactPage();
            app.contact().createNew(new ContactData().withFirstname("TestFirtsName").withMiddlename("Middlename").
                    withLastname("Lastname").withAddress("adr, 66-88 Avenue/Srt.house").withMobilePhone("4654654").withEmail1("London@fg.tyh").
                    withGroup("000").withBirthdayDay("10").withBirthdayMonth("January").withBirthdayYear("2000"), true);
        }
    }

    @Test
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().allContacts().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

    }


}
