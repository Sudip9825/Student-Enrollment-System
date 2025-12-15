package com.sudip.Student.Enrollment.System.core.service;

import com.sudip.Student.Enrollment.System.core.dto.SendMailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    private static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);
 @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender mailSender;
    @Async
    @Override
    public void sendMail(SendMailRequest sendMailRequest) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setFrom(sender);
            helper.setTo(sendMailRequest.getRecipient());
            helper.setSubject(sendMailRequest.getSubject());
            helper.setText(sendMailRequest.getMessage(),true);
            mailSender.send(mimeMessage);
        }
        catch (MessagingException e){
            log.error(e.getMessage(),e);
            throw new MailSendException("Failed to send mail");
        }
    }
}
