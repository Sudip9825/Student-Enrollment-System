package com.sudip.Student.Enrollment.System.core.service;

import com.sudip.Student.Enrollment.System.core.dto.SendMailRequest;

public interface MailService {
    public void sendMail(SendMailRequest sendMailRequest);
}
