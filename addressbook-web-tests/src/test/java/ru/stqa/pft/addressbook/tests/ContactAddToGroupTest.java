package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.io.File;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactAddToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        // если  нет ни одной группы - создаём её
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testName").withFooter("testFooter").withHeader("testHeader"));
        }

        // если  нет ни одного  контакта - создаём его
        if (app.db().contacts().size() == 0) {
            app.goTo().addContactPage();
            File photo = new File("src/test/resources/inner.jpg");

            app.contact().createNew(new ContactData().withFirstname("TestFirtsName").withMiddlename("Middlename").
                    withLastname("Lastname").withAddress("adr").withMobilePhone("4654654").withWorkPhone("66667898").
                    withHomePhone("234523").withEmail1("London@fg.tyh").withBirthdayDay("10").
                    withBirthdayMonth("January").withBirthdayYear("2000").withPhoto(photo), true);
        }
    }


    /**
     * Тест проверяет корректность добавления контакта в групу
     */
    @Test
    public void testContactAddToGroup() {
        app.goTo().homePage();
        logger.info("Формируется список контактов и групп");
        Contacts contactsFromDB = app.db().contacts();
        ContactData oneContact = contactsFromDB.iterator().next();
        Groups groupsFromBD = app.db().groups();
        GroupData oneGroup = groupsFromBD.iterator().next();

        logger.info("Выбор контакта и добавление его к группе");
        app.contact().addToGroup(oneContact, oneGroup);
        app.db().refresh(oneContact);

        logger.info("Проверяем, что контакт успешно добавлен к группе");
        assertThat(oneContact.getGroups(), hasItem(oneGroup));

    }
}