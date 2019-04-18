package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

    /**
     * Тест проверяет корректное создание группы
     */
    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().allGroups();
        GroupData group = new GroupData().withName("testName").withFooter("testFooter").withHeader("testHeader");
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
        Groups after = (Groups) app.group().allGroups();
        assertThat(after, equalTo(before));
    }

}
