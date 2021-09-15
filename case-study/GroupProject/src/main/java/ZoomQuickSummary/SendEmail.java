package ZoomQuickSummary;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
  
  
public class SendEmail 
{
	
	private static String username = System.getProperty("user.name");
	/*
	 * Initializes email sending process
	 */
	public void sendEmail(String filePath) 
	{    
		String recipient = null;
		try {
			recipient = getHostEmail();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// account and password strings
		final String emailAcct = "zoomquicksummary@gmail.com";
		final String emailPass = ""; // REMOVED PASSWORD FOR SECURITY
		
		Properties properties = new Properties();
		
		// setting up mail server
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		// creating session object to get properties  
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailAcct, emailPass);
			}
		});
		
		Message message = prepareMessage(session, emailAcct, recipient, filePath);
		
		try {
			// send email
			Transport.send(message);
			System.out.println("Quick summary successfully sent to " + recipient);
		} catch (MessagingException e) {
			System.out.println("Quick summary failed to send for " + recipient);
			e.printStackTrace();
		}
	}
	
	/*
	 * Allows users to input email recipients via command line.
	 * Can be used in modified form in the final, automated version, e.g.
	 * validate email addresses received from Zoom and add them to list.
	 */
	private static String getHostEmail() throws IOException {
		File file = new File("/Users/"+ username +"/email.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
  
		String host = null;
		host = br.readLine();
		return host;
	}
	
	/*
	 * Creates message with body and chat history .txt attachment
	 */
	private static Message prepareMessage(Session session, String emailAcct, String recipient, String filename) {
		try 
		{
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(emailAcct));
			
			// Can also change RecipientType to .CC or .BCC
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			
			message.setSubject("Zoom Quick Summary");
			
			/* 
			 * Setting html document content for the email 
			 */
			
			// converts html document as a string
			StringBuilder contentBuilder = new StringBuilder();
			try {
			    BufferedReader in = new BufferedReader(new FileReader(filename));
			    String str;
			    while ((str = in.readLine()) != null) {
			        contentBuilder.append(str);
			    }
			    in.close();
			} catch (IOException e) {
			}
			String content = contentBuilder.toString();
			
			BodyPart htmlBodyPart = new MimeBodyPart();
			htmlBodyPart.setContent(content, "text/html");  
			  
			// puts text and attachment parts of the email together
			Multipart multipartObject = new MimeMultipart();  
			multipartObject.addBodyPart(htmlBodyPart);  
			
			message.setContent(multipartObject);
			
			return message; // prepared message to be sent
		}
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
