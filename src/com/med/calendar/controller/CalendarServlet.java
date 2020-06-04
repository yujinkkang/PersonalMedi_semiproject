package com.med.calendar.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.med.calendar.dao.CalendarDao;
import com.med.calendar.dto.CalendarDto;

@WebServlet("/cal")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CalendarServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset =UTF-8");

		String command = request.getParameter("command");
		System.out.printf("<%s>\n", command);
		HttpSession session = request.getSession();

		if (command.equals("insertschedule")) {
			
			int member_seq = (int)session.getAttribute("member_seq");
			String title = request.getParameter("title");
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");

			//System.out.println(start_date_s);

			int res = 0;
				  
			CalendarDto dto = new CalendarDto(); 
			CalendarDao dao = new CalendarDao();
				  
			dto.setMember_seq(member_seq);
			dto.setTitle(title); 
			dto.setStart_date(start_date);
			dto.setEnd_date(end_date);
				  
			res = dao.calInsert(dto);
			

		} else if (command.equals("calendar")) {
			String member_id = (String) request.getAttribute("member_id");
			request.setAttribute("member_id", member_id);

			dispatch("views/calendar/calendar.jsp", request, response);
			
		} else if (command.equals("calendarMain")) {
			dispatch("views/calendar/calendarMain.jsp", request, response);
		}

	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
