package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TestCheckboxMarking extends TestBase {


    @Test
    public void testCheckboxMarking() {
        goTo("НАХОДИТЬ НЕСОВЕРШЕНСТВА");
        click(By.linkText("Софт для быстрого создания скриншотов"));



    }
}
