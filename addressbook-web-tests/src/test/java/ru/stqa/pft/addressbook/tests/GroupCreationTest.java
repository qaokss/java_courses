package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("testName").withFooter("testFooter").withHeader("testHeader");
        app.group().create(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        // находим максимальный id в списке с помощью цикла
//        int max = 0;
//        for (GroupData g : after) {
//            if (g.getId() > max) {
//                max = g.getId();
//            }
//        }

// находим и подставляем максимальный id в список с помощью лямбда-выражения
 //       group.withId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());

        // находим максимальный id с помощью анонимной функции
        group.withId(after.stream().mapToInt( (g) -> g.getId()).max().getAsInt());

        before.add(group);


// сравниваем отсортированные списки
//        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
//        before.sort(byId);
//        after.sort(byId);

        Assert.assertEquals(after, before);
    }

}
