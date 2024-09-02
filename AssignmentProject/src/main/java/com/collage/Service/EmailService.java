package com.collage.Service;

import java.io.File;
import java.io.InputStream;

public interface EmailService {
	 public void sendEmail(String subject, String message, String to);
	 
//	 send multiple email
	 public void sendEmail(String []to, String subject,String message);
	 //send with html
	 
	 public void sendEmailWithHtml(String to, String subject,String htmlContent);
	 //send with file
	 public void sendEmailWithFile(String to, String subject, String message, File file);
	 
	 
	 //if anyone send email then capture
	 public void sendEmailWithFile(String to, String subject, String message, InputStream is);
	 
	 public String getLinkForEmailVerification(String emailToken);
	 
}
