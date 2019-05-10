package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class PasswordChangeByAdministratorTest extends TestBase {


    @Test
    public void testPasswordChangeByAdministrator() throws MessagingException, IOException {
        Users users = app.db().users();
        UserData selectedUser = users.iterator().next();

        logger.info("Авторизация под администратором");
        app.goTo().loginPage();
        app.doAction().loginAsAdmin();

        logger.info("Переход на страницу изменений сведений пользователю и сброс пароля");

        app.goTo().editUserPage();
        app.doAction().choiseUser(selectedUser);

        app.doAction().resetPassword();


        logger.info("Ожидание писем для подтверждения регистрации");

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, selectedUser.getEmail());
        String newpassword = "newpassword";


        logger.info("Письмо получено, завершение регистрации");
        app.registration().finish(confirmationLink, newpassword);

        logger.info("Регистрация завершена, проверка возможности логина под новым пользователем");
        assertTrue(app.newSession().login(selectedUser.getUsername(), newpassword));

    }

}