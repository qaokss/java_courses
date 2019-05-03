package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactDeletionFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        // 1) если  нет ни одной группы - создаём её
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testName").withFooter("testFooter").withHeader("testHeader"));
        }

        // 2) если  нет ни одного  контакта - создаём его
        if (app.db().contacts().size() == 0) {
            app.goTo().addContactPage();
            Groups groups = app.db().groups();
            app.contact().createNew(new ContactData().withFirstname("TestFirtsName").withMiddlename("Middlename").
                    withLastname("Lastname").withAddress("adr").withMobilePhone("4654654").withWorkPhone("66667898").
                    withHomePhone("234523").withEmail1("London@fg.tyh").withBirthdayDay("10").
                    withBirthdayMonth("January").withBirthdayYear("2000").inGroup(groups.iterator().next()), true);
        }

        // 3) находим первый контакт, у которого есть группа, добавляем его в список контактов и выходим из цикла
        Contacts contactsFromDB = app.db().contacts();
        Contacts contacts = new Contacts();
        for (ContactData contact : contactsFromDB) {
            if (contact.getGroups().size() > 0) {
                contacts.add(contact);
                if (contacts.size() > 0) {
                    break;
                }
            }
        }
            // 3.1) если список контактов с группами пуст, то добавляем контакт к группе
            if (contacts.size() == 0) {
                Groups groups = app.db().groups();
                app.contact().addToGroup(contactsFromDB.iterator().next(), groups.iterator().next());

            }

    }

    /**
     * Тест проверяем корректность удаления контакста из группы
     */
    @Test
    public void testContactDeletionFromGroup() {
        app.goTo().homePage();

        logger.info("Формируется список контактов и групп");

        // ищем не пустую группу (в составе которой есть хотя бы один контакт)
        Groups groupsFromBD = app.db().groups();
        GroupData choosingGroup = new GroupData();
        for (GroupData group : groupsFromBD) {
            if (group.getContacts().size() > 0) {
                choosingGroup = group;
                break;
            }
        }
        // выбираем в фильтре наименование не пустой группы
        app.goTo().groupFilter(choosingGroup);

        // контакт вытаскиваем из группы
        ContactData choosingContact = choosingGroup.getContacts().iterator().next();

        logger.info("Уудаление контакта из группы");
        app.contact().removeContactFromGroup(choosingContact);

        app.db().refresh(choosingContact);

        logger.info("Проверка, что контакт успешно удалён из группы");
        assertThat(choosingContact.getGroups(), not(hasItem(choosingGroup)));
    }
}

