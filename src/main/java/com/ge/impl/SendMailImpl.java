package com.ge.impl;

import org.springframework.core.io.FileSystemResource;  
import org.springframework.mail.javamail.JavaMailSenderImpl;  
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;  
import javax.mail.internet.MimeMessage;  
import java.io.File;  

public class SendMailImpl {  
    public static final String DEFALUT_ENCODING = "UTF-8";  
  
    public static void sendtest(JavaMailSenderImpl sender) throws Exception {   	
        String[] ss = {"a462286311@163.com"};
        sendTextWithHtml(sender, ss, "测试简单文本邮件发送！ ", " <a href='http://work.dev.gomeplus.com/'>test</a>测试我的简单邮件发送机制！！ ");  
    }  
  
    public static void sendTextWithHtml(JavaMailSenderImpl sender, String[] tos, String subject, String text)  
            throws Exception {  
        MimeMessage mailMessage = sender.createMimeMessage();  
        initMimeMessageHelper(mailMessage, tos, sender.getUsername(), subject, text);  
        // 发送邮件  
        sender.send(mailMessage);  
  
        System.out.println("邮件发送成功..");  
    }  
  
    public static void sendTextWithImg(JavaMailSenderImpl sender, String[] tos, String subject, String text,  
            String imgId, String imgPath) throws MessagingException {  
        MimeMessage mailMessage = sender.createMimeMessage();  
        MimeMessageHelper messageHelper = initMimeMessageHelper(mailMessage, tos, sender.getUsername(), subject, text,  
                true, true, "GBK");  
        // 发送图片  
        FileSystemResource img = new FileSystemResource(new File(imgPath));  
        messageHelper.addInline(imgId, img);  
        // 发送邮件  
        sender.send(mailMessage);  
  
        System.out.println("邮件发送成功..");  
    }  
  
    public static void sendWithAttament(JavaMailSenderImpl sender, String[] tos, String subject, String text,  
            String AttachName, String filePath) throws MessagingException {  
        MimeMessage mailMessage = sender.createMimeMessage();  
        MimeMessageHelper messageHelper = initMimeMessageHelper(mailMessage, tos, sender.getUsername(), subject, text,  
                true, true, DEFALUT_ENCODING);  
  
        FileSystemResource file = new FileSystemResource(new File(filePath));  
        // 发送邮件  
        messageHelper.addAttachment(AttachName, file);  
        sender.send(mailMessage);  
  
        System.out.println("邮件发送成功..");  
  
    }  
  
    public static void sendWithAll(JavaMailSenderImpl sender, String[] tos, String from, String subject, String text,  
            String imgId, String imgPath, String AttachName, String filePath) throws MessagingException {  
        MimeMessage mailMessage = sender.createMimeMessage();  
        MimeMessageHelper messageHelper = initMimeMessageHelper(mailMessage, tos, sender.getUsername(), subject, text,  
                true, true, DEFALUT_ENCODING);  
  
        // 插入图片  
        FileSystemResource img = new FileSystemResource(new File(imgPath));  
        messageHelper.addInline(imgId, img);  
        // 插入附件  
        FileSystemResource file = new FileSystemResource(new File(filePath));  
        messageHelper.addAttachment(AttachName, file);  
  
        // 发送邮件  
        sender.send(mailMessage);  
  
        System.out.println("邮件发送成功..");  
  
    }  
  
    private static MimeMessageHelper initMimeMessageHelper(MimeMessage mailMessage, String[] tos, String from,  
            String subject, String text) throws MessagingException {  
        return initMimeMessageHelper(mailMessage, tos, from, subject, text, true, false, DEFALUT_ENCODING);  
    }  
  
      
    private static MimeMessageHelper initMimeMessageHelper(MimeMessage mailMessage, String[] tos, String from,  
            String subject, String text, boolean isHTML, boolean multipart, String encoding) throws MessagingException {  
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, multipart, encoding);  
        messageHelper.setTo(tos);  
        messageHelper.setFrom(from);  
        messageHelper.setSubject(subject);  
        // true 表示启动HTML格式的邮件  
        messageHelper.setText(text, isHTML);  
  
        return messageHelper;  
    }  
  
  

}  