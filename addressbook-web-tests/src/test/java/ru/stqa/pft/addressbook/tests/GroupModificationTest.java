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
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("testName").withFooter("testFooter").withHeader("testHeader"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData newGroup = new GroupData().
                withId(modifiedGroup.getId()).withName("testName").withFooter("testFooter").withHeader("testHeader");
        app.group().modify(newGroup);
        Groups after = app.group().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalToObject(before.without(modifiedGroup).withAdded(newGroup)));

    }


}
