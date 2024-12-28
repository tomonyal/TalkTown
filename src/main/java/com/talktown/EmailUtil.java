package com.talktown;

import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtil {

    public static void sendErrorEmail(String subject, String content) {
        // メールの設定
        String to = "tomonyal1224@gmail.com"; // 管理者のメールアドレスを設定
        String from = "noreply@example.com"; // 送信元のメールアドレスを設定
        String host = "smtp.example.com"; // SMTP サーバーを設定

        // プロパティの設定
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "25"); // SMTP ポートを設定

        // セッションの取得
        Session session = Session.getDefaultInstance(properties);

        try {
            // メールメッセージの作成
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(content);

            // メールの送信
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
