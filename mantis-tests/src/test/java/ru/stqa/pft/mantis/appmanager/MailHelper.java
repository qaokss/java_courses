package ru.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {
    private ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app) {
        this.app = app;
        wiser = new Wiser();
    }

    /**
     * Метод предназанчен для ожидания писем на почтовом сервере, т.к. почта приходит не мгновенно
     * @param count - количество одидаемых писем
     * @param timeout - время ожидания писем
     * @return Если почты пришло достаточное кол-во, то полученные письма преобразуются в модельные объекты,
     * модель описана в model -> MailMessage
     */
    public List<MailMessage> waitForMail(int count, long timeout) throws MessagingException, IOException {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout) {
            if (wiser.getMessages().size() >= count) {
                return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error("No mail :(");
    }


    /**
     *  преобразование письма к  "модельному объекту"
     */

    public static MailMessage toModelMail(WiserMessage m) {
        try {
            MimeMessage mm = m.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Метод для поиска ссылки подтверждения регистрации из полученного письма
     * @param mailMessages - теол полученного письма
     * @param email - емейл, на который письмо отправлено
     * @return ссылка для подтверждения регистрации
     */
    public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        // в результате фильтрации в потоке останутся только те сообщения, которые отправлены по нужному адресу, из них
        // выбирается первое
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();

        // С помощью библиотеки Verbalregex вытаскиваем из письма ссылку
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


    /**   запуск почтового сервера  */
    public void start() {
        wiser.start();
    }
    /**   остановка почтового сервера  */
    public void stop() {
        wiser.stop();
    }
}