package com.QuickStartFrame.management.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;


public class SimpleMail {

	// 以HTML格式发送邮件
	public static void sendHtmlMail(MailInfo mailInfo) throws MessagingException {
			Properties props = new Properties();
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.transport.protocol", "smtp");
			// 根据邮件会话属性和密码验证器构造一个发送邮件的session
			Session session = Session.getInstance(props);
			session.setDebug(true);
			
			Message mailMessage = new MimeMessage(session);// 根据session创建一个邮件消息
			Address from = new InternetAddress(mailInfo.getFromAddress());// 创建邮件发送者地址

			mailMessage.setFrom(from);// 设置邮件消息的发送者
			Address[] tos = null;
			String toAdds = mailInfo.getToAddress();
			if (StringUtils.isNotBlank(toAdds)) {
				String[] toArr = toAdds.split(";");
				tos = new Address[toArr.length];
				for (int i = 0; i < toArr.length; i++) {
					tos[i] = new InternetAddress(toArr[i]);
				}
			}
			Address[] ccs = null;
			String ccAdds = mailInfo.getCcAddress();
			if (StringUtils.isNotBlank(ccAdds)) {
				String[] ccArr = ccAdds.split(";");
				ccs = new Address[ccArr.length];
				for (int i = 0; i < ccArr.length; i++) {
					ccs[i] = new InternetAddress(ccArr[i]);
				}
			}
			mailMessage.setRecipients(Message.RecipientType.TO, tos);// 设置邮件消息的接收者
			mailMessage.setRecipients(Message.RecipientType.CC, ccs);
			mailMessage.setSubject(mailInfo.getSubject());// 设置邮件消息的主题
			mailMessage.setSentDate(new Date());// 设置邮件消息发送的时间

			// MimeMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart msgPart = new MimeMultipart("mixed");
			mailMessage.setContent(msgPart);
			
			MimeBodyPart body = new MimeBodyPart();// 表示正文
			msgPart.addBodyPart(body);
			
			
			Multipart contentPart = new MimeMultipart("related");
			body.setContent(contentPart);
			MimeBodyPart content = new MimeBodyPart(); // 文字
			
			contentPart.addBodyPart(content);
            //设置附件的句柄给这个附件对象  
			content.setContent(mailInfo.getContent(),"text/html;charset=utf-8");
			Transport trans = session.getTransport();
			trans.connect(mailInfo.getMailServerHost(), Integer.parseInt(mailInfo.getMailServerPort()), mailInfo.getUsername(), mailInfo.getPassword());
			trans.sendMessage(mailMessage, mailMessage.getAllRecipients());
			
	}

}
