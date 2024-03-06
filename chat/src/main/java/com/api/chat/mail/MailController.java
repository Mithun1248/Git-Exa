package com.api.chat.mail;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/send-mail")
    public String sendMail(@RequestParam String to,@RequestParam String message,@RequestParam String subject){
        mailService.sendmail(to,message,subject);
        return "Sent mail!";
    }

    @PostMapping("/send-html")
    public String sendHtmlMail(@RequestParam String to,@RequestParam String message,@RequestParam String subject) throws MessagingException {
        mailService.sendHtmlEmail(to,message,subject);
        return "Sent mail!";
    }

    @PostMapping("/send-html-template")
    public String sendHtmlTemplate(@RequestParam String to,@RequestParam String message,@RequestParam String subject) throws MessagingException {
        mailService.sendHtmlEmail(to,message,subject);
        return "Sent mail!";
    }

    @PostMapping("/send-html-attachment")
    public String sendHtmlAttachment(@RequestParam String to,@RequestParam String message,@RequestParam String subject) throws MessagingException, IOException {
        mailService.sendHtmlAttachment(to,message,subject);
        return "Sent mail!";
    }
}
