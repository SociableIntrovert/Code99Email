import java.util.*;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.File;
import javax.swing.JOptionPane;
import javax.mail.PasswordAuthentication;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;
import com.sun.mail.smtp.*;
import javax.mail.Message.*;
import javax.mail.internet.*;
import java.util.Scanner;


public class SalvationArmyEREmail 
{
	public static void main(String args[])
	{
		
		
		boolean sent = false;
		ArrayList<String> emaillist= new ArrayList(30);
		Scanner fileInput = null;
		try {
			fileInput = new Scanner(new File("ContactList.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(fileInput.hasNext())
		{
			emaillist.add(fileInput.nextLine());
		}
		for(String email : emaillist)
		{
			String to = email;
			String from = "frankenteg@hotmail.com";
			String subject = "test subject";
			String body = "test body";
			final String username = "email@email.com";
			final String password = "password";
			String host = "smtp.live.com";
		
			Properties prop = new Properties();
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.host", host);
			prop.put("mail.smtp.port", "587");
		
			Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication (username, password);
				}
			});
			
		
		
			
				try {
					SMTPMessage msg = new SMTPMessage(session);
					msg.setFrom(new InternetAddress(from));
					msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
					msg.setSubject(subject);
					msg.setText(body);
					Transport.send(msg);
					sent = true;
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
		} // End for loop
		if(sent)
		{
			JOptionPane.showMessageDialog(null,"Emails have been sent.");
		}
	} // End Main
} //End Class
