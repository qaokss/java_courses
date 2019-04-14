package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("testName").withFooter("testFooter").withHeader("testHeader"));
        }
    }

    @Test
    public void testGroupModification() {
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData newGroup = new GroupData().
                withId(modifiedGroup.getId()).withName("testName").withFooter("testFooter").withHeader("testHeader");
        app.group().modify(newGroup);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());
        before.remove(modifiedGroup);
        before.add(newGroup);

// сравнение отсортированных списков по id
//        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
//        before.sort(byId);
//        after.sort(byId);
//        Assert.assertEquals(after, before);

// сравниваем множества
        Assert.assertEquals(before,after);

    }


}
