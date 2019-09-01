package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
public class TestLinkRedirection extends TestBase {


    /**
     * Тест проверяет, что ссылка "Софт для быстрого создания скриншотов" ведёт на http://monosnap.com/
     */
    @Test
    public void testLinkRedirection() {
        goTo("НАХОДИТЬ НЕСОВЕРШЕНСТВА");
        click(By.linkText("Софт для быстрого создания скриншотов"));
        switchToNewTab();
        Assert.assertEquals(currentLink(), "http://monosnap.com/");
    }
}
