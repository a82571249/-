package com.ge.Demo.Controller;

import java.util.Properties;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliyuncs.exceptions.ClientException;
import com.ge.impl.SendMailImpl;
import com.ge.impl.SendMessage;


@RestController
@RequestMapping("/Apitest")
public class APItest {
@Value("${spring.mail.host}")
private String host;
@Value("${spring.mail.username}")
private String username;
@Value("${spring.mail.password}")
private String password;
@Value("${spring.mail.port}")
private int port;
@Value("${spring.mail.default-encoding}")
private String encoding;



	@RequestMapping("/fuckyou")
	public String fuckyou()
	{
		return "fuckyou";
	}

	@RequestMapping("/sendmessage")
	public String sendmessage() throws ClientException
	{	
		SendMessage sendMessage=new SendMessage();
		String messageback=null;
		messageback=sendMessage.SendMessagea();
		return messageback;
	}
	
	@RequestMapping("/sendmail")	
	public  void sendmail() throws Exception
	{   
        Properties properties = new Properties();  
        properties.setProperty("mail.debug", "true");// 是否显示调试信息(可选)  
        properties.setProperty("mail.smtp.starttls.enable", "false");  
        properties.setProperty("mail.smtp.socketFactory.class",  
                "javax.net.ssl.SSLSocketFactory");  
        properties.setProperty("mail.smtp.auth", "true");  
        properties.put(" mail.smtp.timeout ", " 25000 "); 
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setJavaMailProperties(properties);
		javaMailSender.setHost(host);
        javaMailSender.setUsername(username); // s根据自己的情况,设置username  
        javaMailSender.setPassword(password); // 根据自己的情况, 设置password  
        javaMailSender.setPort(port);  
        javaMailSender.setDefaultEncoding(encoding);  
		SendMailImpl.sendtest(javaMailSender);
	}
}
