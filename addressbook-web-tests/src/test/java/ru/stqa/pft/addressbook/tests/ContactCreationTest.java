package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import java.io.File;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactCreationTest extends TestBase {

//
//    @BeforeMethod
//    public void testGroupCreation() throws Exception {
//        app.goTo().groupPage();
//        if (app.group().allGroups().iterator().next().getName().equals(new GroupData().withName("000"))) {
//            System.out.println("piu");
//        }
//    }


    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().allContacts();
        File photo = new File("src/test/resources/inner.jpg");
        ContactData contact = new ContactData().withFirstname("TestFirtsName").withMiddlename("Middlename").
                withLastname("Lastname").withAddress("adr").withMobilePhone("99999999").withWorkPhone("777777777").
                withHomePhone("234523").withEmail1("London@fg.tyh").withGroup("000").withBirthdayDay("10").
                withBirthdayMonth("January").withBirthdayYear("2000").withTitle("Tittle").withHomepage("qwerty@piu.piu")
                .withPhoto(photo);
        app.goTo().addContactPage();
        app.contact().createNew(contact, true);
        Contacts after = app.contact().allContacts();
        assertEquals(after.size(), before.size() + 1);
        assertThat(after,
                equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


}
