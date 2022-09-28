
package negocio.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarEmail {

	private final Properties props;

	public EnviarEmail() {
		props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		props.put("from", "UO269763@gmail.com");
		props.put("username", "UO269763@gmail.com");
		props.put("password", "estoesunaprueba");
		
	}

	public void enviar(String to, String subject, String content) throws MessagingException {
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
			}
		});
		//System.out.println(session.getProperties());
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(props.getProperty("from")));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(subject);
		message.setText(content);
		Transport.send(message);
	}

}
