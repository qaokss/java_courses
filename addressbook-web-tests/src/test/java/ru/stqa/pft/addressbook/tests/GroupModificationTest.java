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
            app.group().create(new GroupData().withName("testName").withFooter("testFooter").withHeader("testHeader"));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.group().list();
        int indexOfLastGroup = before.size() - 1;
        GroupData newGroup = new GroupData().
                withId(before.get(indexOfLastGroup).getId()).withName("testName").withFooter("testFooter").withHeader("testHeader");
        app.group().modify(newGroup, indexOfLastGroup);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(indexOfLastGroup);
        before.add(newGroup);

// сравнение отсортированных списков по id
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);

// сравниваем множества
//        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }


}
