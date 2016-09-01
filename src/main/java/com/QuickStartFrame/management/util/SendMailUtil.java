package com.QuickStartFrame.management.util;

import javax.mail.MessagingException;

import com.QuickStartFrame.management.config.ConfigTool;
import com.QuickStartFrame.management.mail.MailInfo;
import com.QuickStartFrame.management.mail.SimpleMail;


public class SendMailUtil {	
	/**
	 * @param content 需要添加的内容
	 * @return
	 * @throws MessagingException 
	 */
	public void sendHtmlMail(ConfigTool configTool,String content) throws MessagingException{
			MailInfo mailInfo = new MailInfo();
/*			mailInfo.setMailServerHost(configTool.getMailServerHost());
			mailInfo.setMailServerPort(configTool.getMailServerPort());
			mailInfo.setValidate(true);
			mailInfo.setUsername(configTool.getMailUsername());
			mailInfo.setPassword(configTool.getMailPassword());// 您的邮箱密码
			mailInfo.setFromAddress(configTool.getMailUsername());
			mailInfo.setToAddress(configTool.getMailTo());
			mailInfo.setCcAddress(configTool.getMailCC());
			mailInfo.setSubject(configTool.getMailSubject());*/
			//需要添加内容
			mailInfo.setContent(content);
			SimpleMail.sendHtmlMail(mailInfo);// 发送html格式
	}
	
	public String getMailHeader(){
		StringBuffer sb = new StringBuffer();
		sb.append(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")
				.append("<html>")
				.append("<head>")
				.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")
				.append("<title></title>")
				.append("<style type=\"text/css\">")
				.append("table {border-collapse:collapse;border:1px solid #ddd} table th{border:1px solid #ddd} table td{border:1px solid #ddd}")
				.append("</style>").append("</head>");
		return sb.toString();
	}
}
