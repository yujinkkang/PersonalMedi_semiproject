package com.med.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.med.login.biz.LoginBiz;
import com.med.login.biz.LoginBizImpl;
import com.med.login.dto.LoginDto;


@WebServlet("/send")
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int random = (int) (Math.floor((Math.random() * (99999 - 10000 + 1))) + 10000);
	private String code = random + "";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset =UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("<command : " + command + ">");
		LoginBiz biz = new LoginBizImpl();
		
		if (command.equals("emailsubmit")) {
			Properties props = System.getProperties();
			props.put("mail.smtp.user", "misoti1012");
			props.put("mail.smtp.host","smtp.gmail.com");
			props.put("mail.smtp.port","465");
			props.put("mail.smtp.starttls.enable","true");
			props.put("mail.smtp.auth","true");
			props.put("mail.smtp.socketFactory.port","465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			
			Authenticator auth = new MyAuthentication();
			
			Session session = Session.getDefaultInstance(props,auth);
			MimeMessage msg = new MimeMessage(session);
			
			
			try {
				msg.setSentDate(new Date());
				
				InternetAddress from = new InternetAddress("misoti1012@gmail.com");
				
				msg.setFrom(from);
				
				String member_email = request.getParameter("member_email");
				InternetAddress to = new InternetAddress(member_email);
				
				msg.setRecipient(Message.RecipientType.TO, to);
				msg.setSubject("이메일 인증번호","UTF-8");
				
				System.out.println(code);
				
				msg.setText(code, "UTF-8");
				msg.setHeader("content-Type", "text.html");
				
				javax.mail.Transport.send(msg);
				System.out.println("보냄!");
				
			}catch(AddressException addr_e) {
				addr_e.printStackTrace();
			}catch(MessagingException msg_e) {
				msg_e.printStackTrace();
			}
			
		} else if (command.equals("emailsubmitcheck")) {
			String inputcode = request.getParameter("inputcode");
			System.out.println(inputcode);
			
			if (code.equals(inputcode)) {
				int res = biz.compareEmail(code, inputcode);
				PrintWriter out = response.getWriter();
				out.println(res);
				System.out.println("인증 성공");
				
			} else {
				int res = biz.compareEmail(code, inputcode);
				PrintWriter out = response.getWriter();
				out.println(res);
				System.out.println("인증 실패");
			}
		}
	}

	class MyAuthentication extends Authenticator{
		PasswordAuthentication pa;
		
		public MyAuthentication() {
			String id="misoti1012";
			String pw="projectpassword!!!";
			
			pa = new PasswordAuthentication(id, pw);
		}
		
		public PasswordAuthentication getPasswordAuthentication() {
			return pa;
		}
	}

    public SendMail() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset =UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());

		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset =UTF-8");
		
		service(request, response);
	}
}
