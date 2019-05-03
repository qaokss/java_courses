package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactDeletionTest extends TestBase {


    @BeforeMethod
    public void EnsurePreconditions() {
        app.goTo().homePage();
        // если на странице нет ни одного созданного контакта - создаём его
        if (app.db().contacts().size() == 0) {
            app.goTo().addContactPage();
            app.contact().createNew(new ContactData().withFirstname("TestFirtsName").withMiddlename("Middlename").
                    withLastname("Lastname").withAddress("adr").withMobilePhone("4654654").withWorkPhone("66667898").
                    withHomePhone("234523").withEmail1("London@fg.tyh").withBirthdayDay("10").
                    withBirthdayMonth("January").withBirthdayYear("2000"), true);
        }
    }

    /**
     * Тест проверяет корректность удаления контакта
     */
    @Test
    public void testContactDeletion() {
        logger.info("Формируется список контактов до удаления");
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();

        logger.info("Происходит удаление контакта");
        app.contact().delete(deletedContact);

        logger.info("Формируется список контактов после удаления");
        Contacts after = app.db().contacts();

        logger.info("Сравнивается кол-во контактов до и после удаления");
        assertEquals(after.size(), before.size() - 1);

        logger.info("Сравнивается список контактов до и после удаления");
        assertThat(after, equalToObject(before.without(deletedContact)));


    }


}
