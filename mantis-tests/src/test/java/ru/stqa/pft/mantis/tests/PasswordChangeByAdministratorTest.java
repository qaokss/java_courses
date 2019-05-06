package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class PasswordChangeByAdministratorTest extends TestBase {


    @Test
    public void testPasswordChangeByAdministrator() throws MessagingException, IOException {
        String usernameWithResetedPassword = "";
        String password = "password";

        logger.info("Авторизация под администратором");
        app.goTo().loginPage();
        app.doAction().loginAsAdmin();

        logger.info("Переход на страницу изменений сведений пользователю и сброс пароля");
        app.goTo().editUserPage();

        app.james().initTelnetSession();

        usernameWithResetedPassword = app.doAction().resetPassword();
        String email = String.format("%s@localhost.localdomain", usernameWithResetedPassword);

        logger.info("Ожидание писем для подтверждения регистрации");
//        app.james().createUser(usernameWithResetedPassword, password);

        List<MailMessage> mailMessages = app.james().waitForMail(usernameWithResetedPassword, password, 30000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);


        logger.info("Письмо получено, завершение регистрации");
        app.registration().finish(confirmationLink, password);

        logger.info("Регистрация завершена, проверка возможности логина под новым пользователем");
        assertTrue(app.newSession().login(usernameWithResetedPassword, password));

    }

}