package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.telnet.TelnetClient;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JamesHelper {

    private ApplicationManager app;

    private TelnetClient telnet;
    private InputStream in;
    private PrintStream out;

    private Session mailSession;
    private Store store;
    private String mailserver;

    public JamesHelper(ApplicationManager app) {
        this.app = app;
        telnet = new TelnetClient();
        mailSession = Session.getDefaultInstance(System.getProperties());
    }

    public boolean doesUserExist(String name) {
        initTelnetSession();
        write("verify " + name);
        String result = readUntil("exist");
        closeTelnetSession();
        return result.trim().equals("User " + name + " exist");
    }

    /**
     * Создание нового пользователя по протоколу telnet
     *
     * @param name   - имя нового пользователя
     * @param passwd - пароль новго пользователя
     */
    public void createUser(String name, String passwd) {
        initTelnetSession();
        write("adduser " + name + " " + passwd);
        String result = readUntil("User " + name + " added");
        closeTelnetSession();
    }

    public void deleteUser(String name) {
        initTelnetSession();
        write("deluser " + name);
        String result = readUntil("User " + name + " deleted");
        closeTelnetSession();
    }

    /**
     * Вспомогательный метод для соединения с почтовым сервером под привелигированным пользователем
     */
    private void initTelnetSession() {
        mailserver = app.getProperty("mailserver.host");
        int port = Integer.parseInt(app.getProperty("mailserver.port"));
        String login = app.getProperty("mailserver.adminlogin");
        String password = app.getProperty("mailserver.adminpassword");

        try {
            telnet.connect(mailserver, port);
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Don't know why it doesn't allow login at the first attempt
        readUntil("Login id:");
        write("");
        readUntil("Password:");
        write("");

        // Second login attempt, must be successful
        readUntil("Login id:");
        write(login);
        readUntil("Password:");
        write(password);

        // Read welcome message
        readUntil("Welcome " + login + ". HELP for a list of commands");
    }

    /**
     * Технический метод для чтения данных из входного потока, который выводит сервер на консоль и сраввнения с заданным шаблоном
     */
    private String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();
            while (true) {
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void write(String value) {
        try {
            out.println(value);
            out.flush();
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * отправка команды Quit
     */
    private void closeTelnetSession() {
        write("quit");
    }


    /**
     * Позволяет удалить все письма, полученные пользователем, очистить почтовый ящик
     *
     * @param username - логин пользователя
     * @param password - пароль
     */
    public void drainEmail(String username, String password) throws MessagingException {
        Folder inbox = openInbox(username, password);
        for (Message message : inbox.getMessages()) {
            message.setFlag(Flags.Flag.DELETED, true);
        }
        closeFolder(inbox);
    }

    /**
     * Закрытие папки
     * с параметром true будут удаляться письма, помеченные на удаление
     */
    private void closeFolder(Folder folder) throws MessagingException {
        folder.close(true);
        store.close();
    }


    /**
     * Получение доступа на почтовом сервере к папке INBOX по протоколу POP3
     */
    private Folder openInbox(String username, String password) throws MessagingException {
        store = mailSession.getStore("pop3");
        store.connect(mailserver, username, password);
        Folder folder = store.getDefaultFolder().getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        return folder;
    }

    /**
     * Метод предназанчен для ожидания писем на почтовом сервере, т.к. почта приходит не мгновенно
     *
     * @param username - логин пользователя
     * @param password - пароль пользователя
     * @param timeout  - время ожидания писем
     * @return если есть хоть одно письмо - возвращаем список писем, если нет - ждём и идём на второй заход
     */
    public List<MailMessage> waitForMail(String username, String password, long timeout) throws MessagingException {
        long now = System.currentTimeMillis();
        while (System.currentTimeMillis() < now + timeout) {
            List<MailMessage> allMail = getAllMail(username, password);
            if (allMail.size() > 0) {
                return allMail;
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
     * По протоколу POP3 извлекает сообщения из почтового ящика и превращает в модельные объекты типа MailMessasage
     *
     * @param username - логин пользователя
     * @param password - пароль пользователя
     */
    public List<MailMessage> getAllMail(String username, String password) throws MessagingException {
        Folder inbox = openInbox(username, password);
        List<MailMessage> messages = Arrays.asList(inbox.getMessages()).stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
        closeFolder(inbox);
        return messages;
    }

    /**
     * по протокол POP3
     * Преобразует письма  в модельные объекты типа MailMessasage
     */
    public static MailMessage toModelMail(Message m) {
        try {
            return new MailMessage(m.getAllRecipients()[0].toString(), (String) m.getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}