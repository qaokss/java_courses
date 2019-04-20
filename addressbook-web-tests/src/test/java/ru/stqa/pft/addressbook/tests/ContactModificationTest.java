package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void EnsurePreconditions() {
        app.goTo().homePage();
        // если на странице нет ни одного созданного контакта - создаём его
        if (app.contact().allContacts().size() == 0) {
            app.goTo().addContactPage();
            app.contact().createNew(new ContactData().withFirstname("TFirtsName").withMiddlename("Middle").
                    withLastname("Last").withAddress("adr").withMobilePhone("2222222").withWorkPhone("66667898").
                    withHomePhone("234523").withEmail1("London@fg.tyh").withGroup("000").withBirthdayDay("10").
                    withBirthdayMonth("January").withBirthdayYear("2000"), true);
        }
    }


    @Test
    public void testContactModification() {
        Contacts before = app.contact().allContacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("EditedTest FirtsName").
                withMiddlename("Edited Middlename").withLastname("Edited Lastname").withAddress("Edited adr").
                withMobilePhone("111114654654").withWorkPhone("1111").withHomePhone("234523").withEmail1("EditedLondon@fg.tyh").
                withGroup("000").withBirthdayDay("5").withBirthdayMonth("July").withBirthdayYear("1999").
                withTitle("Edited Tittle").withHomepage("Editedqwerty@piu.piu");

        app.contact().modify(contact, false);
        Contacts after = app.contact().allContacts();

        assertEquals(after.size(),before.size());

        assertThat(after, equalToObject(before.without(modifiedContact).withAdded(contact)));

    }


}

