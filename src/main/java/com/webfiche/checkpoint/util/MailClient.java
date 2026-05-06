package com.webfiche.checkpoint.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
//
import java.util.Properties;
import java.io.*;

public final class MailClient {
	public MailClient() {
	}
	//
	//public static void sendMail(String mailServer, String from, String to,
	public void sendMail(String mailServer,
							String from,
							String to,
							String subject,
							String messageBody,
							String[] attachments)
									throws MessagingException, AddressException
	{
		String moduleName	= "> MailClient.sendMail: ";
		System.out.println(java.util.Calendar.getInstance().getTime()+
							moduleName+"In Send Mail");
		System.out.println(java.util.Calendar.getInstance().getTime()+
				moduleName+"Subject: "+subject);
		System.out.println(java.util.Calendar.getInstance().getTime()+
				moduleName+"Message: "+messageBody);
		// Setup mail server
		Properties props = System.getProperties();
		props.put("mail.smtp.host", mailServer);	
		// Get a mail session
		Session session = Session.getDefaultInstance(props, null);
	
		// Define a new mail message
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		//message.setSubject(subject);
		try {
			message.setSubject(new String(subject.getBytes("UTF-8")));
		} catch(Throwable t) {
			;
		}
		// Create a message part to represent the body text
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(messageBody);
	
		//use a MimeMultipart as we need to handle the file attachments
		Multipart multipart = new MimeMultipart();
	
		//add the message body to the mime message
		multipart.addBodyPart(messageBodyPart);
	
		// add any file attachments to the message
		addAtachments(attachments, multipart);
	
		// Put all message parts in the message
		message.setContent(multipart);
	
		// Send the message
		Transport.send(message);
	}
	//protected static void addAtachments(String[] attachments, Multipart multipart)
	protected void addAtachments(String[] attachments, Multipart multipart)
			throws MessagingException, AddressException	{
		String moduleName	= "> MailClient.addAttachments: ";
		//System.out.println(java.util.Calendar.getInstance().getTime()+
		//		   moduleName+"In Add Attachments");
		for (String filePath : attachments) {
			MimeBodyPart attachPart = new MimeBodyPart();
			try {
				System.out.println(java.util.Calendar.getInstance().getTime()+
						moduleName+"File Path & Name: "+ filePath);
				attachPart.attachFile(filePath);
				System.out.println(java.util.Calendar.getInstance().getTime()+
						moduleName+"File Name: "+ attachPart.getFileName());
				multipart.addBodyPart(attachPart);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
	    }
		/*
		for(int i = 0; i<= attachments.length -1; i++) {
			String filename = attachments[i];
			MimeBodyPart attachmentBodyPart = new MimeBodyPart();
	    
			//use a JAF FileDataSource as it does MIME type detection
			FileDataSource source = new FileDataSource(filename);
			attachmentBodyPart.setDataHandler(new DataHandler(source));
	    
			//assume that the filename you want to send is the same as the
			//actual file name - could alter this to remove the file path
			String justFileName	= filename.substring(filename.lastIndexOf("/")+1);
			//attachmentBodyPart.setFileName(filename);
			attachmentBodyPart.setFileName(justFileName);
	    
			//add the attachment
			multipart.addBodyPart(attachmentBodyPart);
		}
		*/
	}
    //
    //public static void main(String[] args) {
    //	try {
    //	    //MailClient client = new MailClient();
    //	    //String server="mail.webfiche.com";
    //	    String server="smtp.comcast.net";
    //	    //String from="NO_REPLY@aaa.com";
    //	    String from="andy.patil@webfiche.com";
    //	    String to = "andy.patil@webfiche.com";
    //	    String subject="Test";
    //	    String message="Testing";
    //	    String[] filenames =
    //		{"somefile.txt"};
    //	    //client.sendMail(server,from,to,subject,message,filenames);
    //	    sendMail(server,from,to,subject,message,filenames);
    //	}
    //	catch(Exception e) {
    //	    e.printStackTrace(System.out);
    //	}
    //}
}
