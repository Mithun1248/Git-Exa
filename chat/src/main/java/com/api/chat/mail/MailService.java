package com.api.chat.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendmail(String to,String message,String subject){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom("csmdvg1@gmail.com"); //Can't be overridden in gmail smtp
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    public void sendHtmlEmail(String to, String message, String subject) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//        helper.setFrom("hello@demomailtrap.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText("<h1>Hello This is html mail!</h1>" +
                "<p>"+message+"</p>", true);
        mailSender.send(mimeMessage);
    }

    public void sendHtmlTemplate(String to, String message, String subject) throws MessagingException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        Path path = Paths.get("D:/files/random.txt");
        String htmlTemplate =Files.readString(path, StandardCharsets.UTF_8);
        String htmlBody = htmlTemplate.replace("${message}",message);
//        helper.setFrom("hello@demomailtrap.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        mailSender.send(mimeMessage);
    }

    public void sendHtmlAttachment(String to, String message, String subject) throws MessagingException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        Path path = Paths.get("D:/files/random.txt");
        String htmlTemplate =Files.readString(path, StandardCharsets.UTF_8);
        String htmlBody = htmlTemplate.replace("${message}",message);
//        helper.setFrom("mithunsnayak389@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        FileSystemResource fileSystemResource = new FileSystemResource(new File("C:/Users/ee212569/Downloads/chat_icon.png"));
        helper.addAttachment("Image.png",fileSystemResource);
        mailSender.send(mimeMessage);
    }
}
