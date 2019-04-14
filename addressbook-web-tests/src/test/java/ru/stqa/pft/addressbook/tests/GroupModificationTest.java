package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test1", "qwe", "rty"));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData(before.get(before.size() - 1).getId(), "110", "test555", "test678");
        int lastGroup = before.size() - 1;

        app.group().modify(group, lastGroup);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(lastGroup);
        before.add(group);

// сравнение отсортированных списков по id
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);

// сравниваем множества
//        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }


}
