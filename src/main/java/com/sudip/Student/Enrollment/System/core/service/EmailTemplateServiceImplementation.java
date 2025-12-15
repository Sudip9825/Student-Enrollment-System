package com.sudip.Student.Enrollment.System.core.service;

import com.sudip.Student.Enrollment.System.core.dto.SendMailRequest;
import com.sudip.Student.Enrollment.System.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailTemplateServiceImplementation implements EmailTemplateService {
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;


    @Override
    public void sendMail(User user) {
        Context context =new Context();
        context.setVariable("username", user.getUsername());
        context.setVariable("email", user.getEmail());
        String message=templateEngine.process("email/welcome-email",context);

        SendMailRequest sendMailRequest=new SendMailRequest();
        sendMailRequest.setRecipient(user.getEmail());
        sendMailRequest.setSubject("user successfully registerd");
        sendMailRequest.setMessage(message);
        mailService.sendMail(sendMailRequest);

    }
}
