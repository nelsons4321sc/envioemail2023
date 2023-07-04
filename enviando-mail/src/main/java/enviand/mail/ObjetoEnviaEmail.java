package enviand.mail;

/*
 * LINK COM DICAS PARA CONSEGUIR ENVIAR EMAIL PELO JAVA APARTIR DO GMAIL 
 * https://support.google.com/accounts/answer/185833?hl=pt-BR
 * Como usá-lo
	Acesse as configurações da sua 
	Conta do Google no aplicativo ou 
	dispositivo que está tentando configurar. 
	Substitua sua senha pela senha de 16 caracteres mostrada acima.
	Assim como sua senha normal, 
	esta senha de aplicativo concede acesso completo à sua Conta do Google. 
	Você não precisará se lembrar, então não escreva ou compartilhe com ninguém.
 */

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ObjetoEnviaEmail {

	private String userName = "nelsonsouto16@gmail.com";
	private String senha = "n g n l pic m n x h k b o t b";
	
	private String listaDestinatário ="nelsonscosta@yahoo.com.br";
	private String nomeRemetente = "";
	private String assuntoEmail= "";
	private String textoEmail= "";
	private String filePath = "/caminho/para/arquivo/anexo.txt";
	
	public ObjetoEnviaEmail(String listaDestinatário,String nomeRemetente, String assuntoEmail, String textoEmail ) {
		this.listaDestinatário = listaDestinatário;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}

	public void enviarEmail(boolean envioHtml) throws Exception {

		Properties properties = new Properties();

		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(userName, senha);
			}

		});

		Address[] toUser = InternetAddress.parse("nelsonsouto16@gmail.com, nelsonscosta@yahoo.com.br, nelsonscosta@aol.com");
		//Address[] toUser = InternetAddress.parse(listaDestinatário);

		Message message = new MimeMessage(session);

		//message.setFrom(new InternetAddress(userName, "Nelson - Programador em Java"));
		message.setFrom(new InternetAddress(userName, nomeRemetente));
		message.setRecipients(Message.RecipientType.TO, toUser);//quem estou enviando
		//message.setSubject("Chegou email enviado com java");//Assunto do e-mail
		message.setSubject(assuntoEmail); // Assunto do e-mail
		
		if(envioHtml) {
			message.setContent(textoEmail, "text/html; charset=utf-8");
		} else {
			message.setText(textoEmail);
		}
		
		
		

		Transport.send(message);
		// Thread.sleep(5000);
	}
}
