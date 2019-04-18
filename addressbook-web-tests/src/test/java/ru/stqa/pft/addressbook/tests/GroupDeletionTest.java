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
        if (app.group().allGroups().size() == 0) {
            app.group().create(new GroupData().withName("testName").withFooter("testFooter").withHeader("testHeader"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {

        Groups before = app.group().allGroups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertEquals(app.group().count(), before.size() - 1);
        Groups after = app.group().allGroups();
        assertThat(after, equalToObject(before.without(deletedGroup)));
    }


}
