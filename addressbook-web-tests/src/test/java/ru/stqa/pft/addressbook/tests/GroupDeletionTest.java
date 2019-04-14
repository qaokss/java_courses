package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test1", "qwe", "rty"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {

        List<GroupData> before = app.group().list();
        int lastGroup = before.size() - 1;
        app.group().delete(lastGroup);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), lastGroup);
        before.remove(lastGroup);
        Assert.assertEquals(before, after);
        }


}
