package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[] {new GroupData().withName("group name 1").withHeader("header 1").withFooter("footer 1")});
        list.add(new Object[] {new GroupData().withName("group name 2").withHeader("header 2").withFooter("footer 2")});
        list.add(new Object[] {new GroupData().withName("group name 2").withHeader("header 3").withFooter("footer 3")});
        return list.iterator();
    }

    /**
     * Тест проверяет корректное создание группы
     */
    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().allGroups();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().allGroups();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt( (g) -> g.getId()).max().getAsInt()))));
    }

    /**
     * Тест проверяет, что нельзя создать группу с запрещённым символом '
     */
    @Test
    public void testBadGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().allGroups();
        GroupData group = new GroupData().withName("testName'").withFooter("testFooter").withHeader("testHeader");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().allGroups();
        assertThat(after, equalTo(before));
    }

}
