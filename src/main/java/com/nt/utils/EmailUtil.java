package com.nt.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	 @Autowired
	 private JavaMailSender mailSender;
	 
	    public Boolean sendEmail(String subject, String body, String to)
	    {
	    	MimeMessage mimeMessage = mailSender.createMimeMessage();		 
	    	
	        try {

	            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

	            mimeMessageHelper.setTo(to);
	            mimeMessageHelper.setText(body);
	            mimeMessageHelper.setSubject(subject);

	            mailSender.send(mimeMessageHelper.getMimeMessage());
	            return true;
	        }
	 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
}
