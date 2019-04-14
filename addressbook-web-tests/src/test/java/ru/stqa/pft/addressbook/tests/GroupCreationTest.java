package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData("test123", "test2", "test3");
        app.group().create(group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);

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

        // сравниваем множества
        // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

// сравниваем отсортированные списки
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
