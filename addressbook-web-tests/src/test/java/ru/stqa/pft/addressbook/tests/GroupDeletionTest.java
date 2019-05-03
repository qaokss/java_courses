package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;


public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("testName").withFooter("testFooter").withHeader("testHeader"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        logger.info("Формируется список групп до удаления");
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();

        logger.info("Происходит удаление группы");
        app.group().delete(deletedGroup);

        logger.info("Сравнивается кол-во групп до и после удаления");
        assertEquals(app.group().count(), before.size() - 1);

        logger.info("Формируется список групп после удаления");
        Groups after = app.db().groups();

        logger.info("Сравнивается список групп до и после удаления");
        assertThat(after, equalToObject(before.without(deletedGroup)));

        verifyGroupListInUI();
    }


}
