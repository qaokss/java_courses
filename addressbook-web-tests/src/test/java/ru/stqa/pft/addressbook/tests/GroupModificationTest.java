package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testName").withFooter("testFooter").withHeader("testHeader"));
        }
    }

    /**
     * Тест проверяет корректность модификации группы.
     * Тест отключен, т.к реализовано более быстрый вариант
     */
    @Test (enabled = false)
    public void testGroupModification() {
        app.goTo().groupPage();
        logger.info("Формируется список групп до модификации");
        Groups before = app.group().allGroups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData newGroup = new GroupData().
                withId(modifiedGroup.getId()).withName("testName").withFooter("testFooter").withHeader("testHeader");
        logger.info("Происходит модификация группы");
        app.group().modify(newGroup);
        logger.info("Сравнивается кол-во групп на странице до и после модификации");
        assertEquals(app.group().count(), before.size());
        logger.info("Формируется список групп после модификации");
        Groups after = app.group().allGroups();
        logger.info("Сравнивается список групп до и после модификации");
        assertThat(after, equalToObject(before.without(modifiedGroup).withAdded(newGroup)));

    }

    /**
     * Тест проверяет корректность модификации группы. Это более быстрый вариант, данные для сравнения берутся не из UI,
     * а тянутся напрямую из БД
     */
    @Test
    public void testGroupModificationDB() {
        app.goTo().groupPage();
        logger.info("Формируется список групп до модификации");
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();

        GroupData newGroup = new GroupData().
                withId(modifiedGroup.getId()).withName("testName").withFooter("testFooter").withHeader("testHeader");

        logger.info("Происходит модификация группы");
        app.group().modify(newGroup);

        logger.info("Сравнивается кол-во групп до и после модификации");
        assertEquals(app.group().count(), before.size());

        logger.info("Формируется список групп после модификации");
        Groups after = app.db().groups();

        logger.info("Сравнивается список групп до и после модификации");
        assertThat(after, equalToObject(before.without(modifiedGroup).withAdded(newGroup)));

    }

}
