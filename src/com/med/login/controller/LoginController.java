package com.med.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.med.calendar.dao.CalendarDao;
import com.med.calendar.dto.CalendarDto;
import com.med.login.biz.LoginBiz;
import com.med.login.biz.LoginBizImpl;
import com.med.login.dto.LoginDto;

@WebServlet("/logincontroller.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public LoginController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("<command : " + command + ">");
		
		LoginBiz biz = new LoginBizImpl();
		HttpSession session = request.getSession();
		
		if (command.equals("main")) {
			dispatch("views/login/main.jsp", request, response);
			
		} else if (command.equals("loginclick")) {
			dispatch("views/login/login.jsp", request, response);
			
		} else if (command.equals("login")) {
			String member_id = request.getParameter("id");
			String member_pw = request.getParameter("pw");
			LoginDto loginDto = biz.login(member_id, member_pw);
			
			if (loginDto == null) {
				jsResponse("아이디와 비밀번호를 확인해 주세요", "logincontroller.do?command=loginclick", response);
				
			} else if(loginDto.getMember_enabled().equals("Y")) {
				jsResponse("탈퇴한 회원입니다", "logincontroller.do?command=loginclick", response);
				
			} else if (loginDto.getMember_enabled().equals("N")) {
				session.setAttribute("loginDto", loginDto);
				session.setAttribute("member_seq", loginDto.getMember_seq());
				session.setAttribute("member_id", loginDto.getMember_id());
				
				int member_seq = loginDto.getMember_seq();
				CalendarDao dao = new CalendarDao();
				List<CalendarDto> calList = dao.checkMemberList(member_seq);
				session.setAttribute("calList", calList);
				
				session.setMaxInactiveInterval(60*60);
				loginSuccess("로그인 성공", response);
				
			}
			
		} else if (command.equals("logout")) {
			session.invalidate();
			response.sendRedirect("logincontroller.do?command=main");
			
		} else if (command.equals("registMember")) {
			request.setAttribute("member_sns", "M");
			dispatch("views/login/registMember.jsp", request, response);
			
		} else if (command.equals("insertMember")) {
			String member_sns = request.getParameter("member_sns");
			String member_id = request.getParameter("member_id");
			String member_pw = request.getParameter("member_pw");
			String member_name = request.getParameter("member_name");
			String member_birth_yy = request.getParameter("member_birth_yy");
			String member_birth_mm = request.getParameter("member_birth_mm");
			String member_birth_dd = request.getParameter("member_birth_dd");
			String member_birth = member_birth_yy + member_birth_mm + member_birth_dd;
			String member_gender = request.getParameter("member_gender");
			String member_email = request.getParameter("member_email");
			String member_addr = request.getParameter("member_addr");
			String member_phone1 = request.getParameter("member_phone1");
			String member_phone2 = request.getParameter("member_phone2");
			String member_phone3 = request.getParameter("member_phone3");
			String member_phone = member_phone1 + "-" + member_phone2 + "-" + member_phone3;
			/*
			 * String member_sns = "M"; String member_enabled = "N";
			 */
			LoginDto loginDto = new LoginDto(member_sns, member_id, member_pw, member_name, member_birth, 
									member_gender, member_email, member_addr, member_phone);
			int res = biz.insertMember(loginDto);
			
			if (res > 0) {
				loginSuccess("회원가입에 성공하였습니다 로그인해 주세요", response);
			} else {
				loginSuccess("회원가입에 실패하였습니다", response);
			}
			
		} else if (command.equals("idCheck")) {
			String member_id = request.getParameter("member_id");
			int res = biz.idCheck(member_id);
			
			PrintWriter out = response.getWriter();
			out.println(res);
			
		} else if (command.equals("emailCheck")) {
			String member_email = request.getParameter("member_email");
			int res = biz.emailCheck(member_email);
			PrintWriter out = response.getWriter();
			out.println(res);
			
		} else if (command.equals("snsCheck")) {
			String member_sns = request.getParameter("sns").toUpperCase();
			String member_email = request.getParameter("member_email");
			
			int res = biz.snsCheck(member_sns, member_email);
			
			if (res > 0) {
				LoginDto snsDto = biz.snsLogin(member_email);

				session.setAttribute("loginDto", snsDto);
				session.setAttribute("member_seq", snsDto.getMember_seq());
				session.setAttribute("member_id", snsDto.getMember_id());
				session.setMaxInactiveInterval(60*60);
				
				loginSuccess("로그인 성공", response);
				
			} else {
				request.setAttribute("member_email", member_email);
				request.setAttribute("member_sns", member_sns);
				
				dispatch("views/login/registMember.jsp", request, response);
			}
			
			
			//LoginDto dto = biz.snsCheck(member_sns);
			
			
		} else if (command.equals("imageUpload_type1")) {
			dispatch("views/image/imageUpload_type1.jsp", request, response);

		} else if (command.equals("imageUpload_type2")) {
			dispatch("views/image/imageUpload_type2.jsp", request, response);

		} else if (command.equals("imageUpload_type3")) {
			dispatch("views/image/imageUpload_type3.jsp", request, response);

		} else if (command.equals("xmlUpload_type1")) {
			dispatch("views/upload/xmlUpload_type1.jsp", request, response);

		} else if (command.equals("xmlUpload_type2")) {
			dispatch("views/upload/xmlUpload_type2.jsp", request, response);

		} else if (command.equals("xmlUpload_type3")) {
			dispatch("views/upload/xmlUpload_type3.jsp", request, response);

		} else if (command.equals("locationSearch")) {
			dispatch("views/hospital/locationMain.jsp", request, response);
			
		} else if (command.equals("aboutPersonalMedi")) {
			dispatch("views/login/aboutPersonalMedi.jsp", request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		doGet(request, response);
	}
	
	private void loginSuccess(String msg, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"')");
		out.println("location.href='logincontroller.do?command=main'");
		out.println("</script>");
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"')");
		out.println("location.href='"+url+"'");
		out.println("</script>");
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
