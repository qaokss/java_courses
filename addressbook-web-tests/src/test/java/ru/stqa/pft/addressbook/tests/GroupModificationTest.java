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
        if (app.group().allGroups().size() == 0) {
            app.group().create(new GroupData().withName("testName").withFooter("testFooter").withHeader("testHeader"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.group().allGroups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData newGroup = new GroupData().
                withId(modifiedGroup.getId()).withName("testName").withFooter("testFooter").withHeader("testHeader");
        app.group().modify(newGroup);
        assertEquals(app.group().count(), before.size());
        Groups after = app.group().allGroups();
        assertThat(after, equalToObject(before.without(modifiedGroup).withAdded(newGroup)));

    }


}
