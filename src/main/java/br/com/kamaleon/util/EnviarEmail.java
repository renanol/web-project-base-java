package br.com.kamaleon.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnviarEmail {
	
	private String mailSMTPServer;
	private String mailSMTPServerPort;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	private String login;
	private String senha;
	
	/*
	 * quando instanciar um Objeto ja sera atribuido o servidor SMTP do GMAIL 
	 * e a porta usada por ele
	 */
	public EnviarEmail() { //Para o GMAIL 
		mailSMTPServer = "smtp.gmail.com";
		mailSMTPServerPort = "465";
	}
	/*
	 * caso queira mudar o servidor e a porta, so enviar para o contrutor
	 * os valor como string
	 */
	EnviarEmail(String mailSMTPServer, String mailSMTPServerPort) { //Para outro Servidor
		this.mailSMTPServer = mailSMTPServer;
		this.mailSMTPServerPort = mailSMTPServerPort;
	}
	
	public void sendMail(String from, String to, String subject, String message, ArrayList<String> listaArquivos) throws MessagingException {
		
		Properties props = new Properties();

		props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL
		props.put("mail.smtp.auth", "true"); //ativa autenticacao
		props.put("mail.smtp.user", from); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", mailSMTPServerPort); //porta
		props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		//Cria um autenticador que sera usado a seguir
		SimpleAuth auth = null;
		auth = new SimpleAuth (getLogin(),getSenha());
		
		//Session - objeto que ira realizar a conexão com o servidor
		/*Como há necessidade de autenticação é criada uma autenticacao que
		 * é responsavel por solicitar e retornar o usuário e senha para 
		 * autenticação */
		Session session = Session.getDefaultInstance(props, auth);
		session.setDebug(true); //Habilita o LOG das ações executadas durante o envio do email

		//Objeto que contém a mensagem
		
		
		// Create the message part 
		Message msg = new MimeMessage(session);
        BodyPart messageBodyPart = new MimeBodyPart();
        // Fill the message
        messageBodyPart.setText(message);

        // Create a Multipart
        Multipart multipart = new MimeMultipart();

        // Add part one
        multipart.addBodyPart(messageBodyPart);
        
        if(listaArquivos != null)
        {
        	for(String arquivo : listaArquivos)
        	{
        		File file = new File(arquivo);
        		
        		if (file != null)
        		{
        			
        			// Create second body part
        			messageBodyPart = new MimeBodyPart();
        			
        			// Get the attachment
        			DataSource source = new FileDataSource(file);
        			
        			// Set the data handler to the attachment
        			messageBodyPart.setDataHandler(new DataHandler(source));
        			
        			// Set the filename
        			messageBodyPart.setFileName(file.getName());
        			
        			// Add part two
        			multipart.addBodyPart(messageBodyPart);
        		}
        	}
        }
        
		msg.setContent(multipart);
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setFrom(new InternetAddress(from));
		msg.setSubject(subject);
		
			
		
		//Objeto encarregado de enviar os dados para o email
		Transport tr;
		try {
			tr = session.getTransport("smtp"); //define smtp para transporte
			/*
			 *  1 - define o servidor smtp
			 *  2 - seu nome de usuario do gmail
			 *  3 - sua senha do gmail
			 */
			tr.connect(mailSMTPServer, getLogin(), getSenha());
			msg.saveChanges(); // don't forget this
			//envio da mensagem
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(">> Erro: Envio Mensagem");
			e.printStackTrace();
		}

	}
	
	public void sendMailRetaguarda(String from, String to, String subject, String message, ArrayList<String> listaArquivos) throws MessagingException {
		
		Properties props = new Properties();
		
		props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL
		props.put("mail.smtp.auth", "true"); //ativa autenticacao
		props.put("mail.smtp.user", from); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", mailSMTPServerPort); //porta
		props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		//Cria um autenticador que sera usado a seguir
		SimpleAuth auth = null;
		auth = new SimpleAuth (getLogin(),getSenha());
		
		//Session - objeto que ira realizar a conexão com o servidor
		/*Como há necessidade de autenticação é criada uma autenticacao que
		 * é responsavel por solicitar e retornar o usuário e senha para 
		 * autenticação */
		Session session = Session.getInstance(props, auth);
		session.setDebug(false); //Habilita o LOG das ações executadas durante o envio do email
		
		//Objeto que contém a mensagem
		
		
		// Create the message part 
		Message msg = new MimeMessage(session);
		BodyPart messageBodyPart = new MimeBodyPart();
		// Fill the message
		messageBodyPart.setText(message);
		
		// Create a Multipart
		Multipart multipart = new MimeMultipart();
		
		// Add part one
		multipart.addBodyPart(messageBodyPart);
		
		if(listaArquivos != null)
		{
			for(String arquivo : listaArquivos)
			{
				File file = new File(arquivo);
				
				if (file != null)
				{
					
					// Create second body part
					messageBodyPart = new MimeBodyPart();
					
					// Get the attachment
					DataSource source = new FileDataSource(file);
					
					// Set the data handler to the attachment
					messageBodyPart.setDataHandler(new DataHandler(source));
					
					// Set the filename
					messageBodyPart.setFileName(file.getName());
					
					// Add part two
					multipart.addBodyPart(messageBodyPart);
				}
			}
		}
		
		msg.setContent(multipart);
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setFrom(new InternetAddress(from));
		msg.setSubject(subject);
		
		
		
		//Objeto encarregado de enviar os dados para o email
		Transport tr;
		try {
			tr = session.getTransport("smtp"); //define smtp para transporte
			/*
			 *  1 - define o servidor smtp
			 *  2 - seu nome de usuario do gmail
			 *  3 - sua senha do gmail
			 */
			tr.connect(mailSMTPServer, getLogin(), getSenha());
			msg.saveChanges(); // don't forget this
			//envio da mensagem
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(">> Erro: Envio Mensagem");
			e.printStackTrace();
		}
		
	}
	
public void sendMailHtmlRetaguarda(String from, String to, String subject, String message, ArrayList<String> listaArquivos) throws MessagingException {
		
		Properties props = new Properties();
		
		props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL
		props.put("mail.smtp.auth", "true"); //ativa autenticacao
		props.put("mail.smtp.user", from); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", mailSMTPServerPort); //porta
		props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		//Cria um autenticador que sera usado a seguir
		SimpleAuth auth = null;
		auth = new SimpleAuth (getLogin(),getSenha());
		
		//Session - objeto que ira realizar a conexão com o servidor
		/*Como há necessidade de autenticação é criada uma autenticacao que
		 * é responsavel por solicitar e retornar o usuário e senha para 
		 * autenticação */
		Session session = Session.getInstance(props, auth);
		session.setDebug(false); //Habilita o LOG das ações executadas durante o envio do email
		
		//Objeto que contém a mensagem
		
		
		// Create the message part 
		Message msg = new MimeMessage(session);
		BodyPart messageBodyPart = new MimeBodyPart();
		// Fill the message
		messageBodyPart.setContent(message, "text/html");
		
		// Create a Multipart
		Multipart multipart = new MimeMultipart();
		
		// Add part one
		multipart.addBodyPart(messageBodyPart);
		
		if(listaArquivos != null)
		{
			for(String arquivo : listaArquivos)
			{
				File file = new File(arquivo);
				
				if (file != null)
				{
					
					// Create second body part
					messageBodyPart = new MimeBodyPart();
					
					// Get the attachment
					DataSource source = new FileDataSource(file);
					
					// Set the data handler to the attachment
					messageBodyPart.setDataHandler(new DataHandler(source));
					
					// Set the filename
					messageBodyPart.setFileName(file.getName());
					
					// Add part two
					multipart.addBodyPart(messageBodyPart);
				}
			}
		}
		
		msg.setContent(multipart);
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setFrom(new InternetAddress(from));
		msg.setSubject(subject);
		
		
		
		//Objeto encarregado de enviar os dados para o email
		Transport tr;
		try {
			tr = session.getTransport("smtp"); //define smtp para transporte
			/*
			 *  1 - define o servidor smtp
			 *  2 - seu nome de usuario do gmail
			 *  3 - sua senha do gmail
			 */
			tr.connect(mailSMTPServer, getLogin(), getSenha());
			msg.saveChanges(); // don't forget this
			//envio da mensagem
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(">> Erro: Envio Mensagem");
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		EnviarEmail teste = new EnviarEmail();
		teste.setLogin("vinicius.stephano@gmail.com");
		teste.setSenha("20091946");
		ArrayList<String> lista = new ArrayList();
		lista.add("\\kerp\\relatorios\\10024.pdf");
		lista.add("\\kerp\\relatorios\\10025.pdf");
		
		try {
			teste.sendMail("vinicius.stephano@gmail.com", "vistephano@gmail.com", "teett", "isso é apenas um teste!", lista);
			teste.sendMail("vinicius.stephano@gmail.com", "vinicius@kamaleon.com.br", "cabeçalhsoasdddas", "isso é apenas um teste!", null);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

//clase que retorna uma autenticacao para ser enviada e verificada pelo servidor smtp
class SimpleAuth extends Authenticator {
	public String username = null;
	public String password = null;


	public SimpleAuth(String user, String pwd) {
		username = user;
		password = pwd;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication (username,password);
	}
} 
