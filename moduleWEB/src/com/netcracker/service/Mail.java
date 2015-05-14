package com.netcracker.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 *
 * @author NikichXP
 */

public class Mail {

    //A LOT OF SHIT BELOW!!!!

    private final static String username = "nctc.taxi.service@gmail.com";
    private final static String password = "9asdfq00"; //Special password for an application

    private final static String DASHBOARD_ADDR = "students2015.cloudapp.net/user/dashboard"; //TODO Set normal address for dashboard.
    /* Constant mail patterns */
    private final static String LOGINMSG = "Hello, dear user, to validate your e-mail click the link below:\n https://trello.com/c/C7eCyla3/21-tasks-mandatory";
    private final static String CLIENTNOTIFY1 = "Your order changed status to \"in progress\". You can see all your orders in dashboard:" +
             DASHBOARD_ADDR + " or link below to see full details: ";




    public static String test2 () {
        return testSend("nikichx2@gmail.com", "someTheme", LOGINMSG);
    }

    public static String notifyUser (String email, String orderId) {
        return "not yet ready";
    }
    public static String testSend(String email, String theme, String text) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject(theme);
            message.setText(text);
            Transport.send(message);
            return "Mail sent";
        } catch (Exception e) {
            return e.toString();
        }
    }
}
