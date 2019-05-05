package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTest extends TestBase {

//    @BeforeMethod
//    public void startMailServer() {
//        //запуск почтового сервера перед тестом
//        app.mail().start();
//    }


    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String username = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);

        app.james().createUser(username, password);

        logger.info("Ввод регистрационных данных, нажатие 'Зарегистрироваться'");
        app.registration().start(username, email);

        logger.info("Ожидание писем для подтверждения регистрации");
        //   List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000); //письмо из встроенного почтового сервера

        List<MailMessage> mailMessages = app.james().waitForMail(username, password, 60000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);

        logger.info("Письмо получено, завершение регистрации");
        app.registration().finish(confirmationLink, password);

        logger.info("Регистрация завершена, проверка возможности логина под новым пользователем");
        assertTrue(app.newSession().login(username, password));

    }


//   @AfterMethod(alwaysRun = true)
//    public void stopMailServer() {
//        //остановка почтового сервера после теста
//        app.mail().stop();
//    }
}