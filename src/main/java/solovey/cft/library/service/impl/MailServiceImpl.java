package solovey.cft.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import solovey.cft.library.dto.BookDto;

@Service
public class MailServiceImpl {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String clientMail, BookDto book){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(clientMail);
        mailMessage.setSubject("Book return");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Please return book:").append(book.getTitle()).toString();
        mailMessage.setText(stringBuilder.toString());

        mailSender.send(mailMessage);
    }


}
