package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test789", "test2", "test3");
        app.getGroupHelper().createGroup(group);
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);

        // находим максимальный id в списке с помощью цикла
//        int max = 0;
//        for (GroupData g : after) {
//            if (g.getId() > max) {
//                max = g.getId();
//            }
//        }

        // находим и подставляем максимальный id в список с помощью лямбда-выражения
        group.setId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());

        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}
