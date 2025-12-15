package com.sudip.Student.Enrollment.System.core.service;

import com.sudip.Student.Enrollment.System.entity.User;

public interface EmailTemplateService {
    public void sendMail(User user);
}
