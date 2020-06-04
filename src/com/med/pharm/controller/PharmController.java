package com.med.pharm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import com.med.pharm.biz.PharmBiz;

/**
 * Servlet implementation class PharmController
 */
@WebServlet("/PharmService.do")
public class PharmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PharmController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("<command : " + command + ">");
		
		PharmBiz biz = new PharmBiz();
		
		HttpSession session = request.getSession();
		
		if(command.equals("pharmList")) {
				dispatch("views/pharm/pharmService.jsp", request, response);
		} else if(command.equals("PharmSearch")) {
			String sido = request.getParameter("sido");
			String sigungu = request.getParameter("sigungu");
			String qn = request.getParameter("qn");
			
//			System.out.println(sido+sigungu+qn);
			
			
			try {
				List<HashMap<String, String>> list = (List<HashMap<String, String>>) biz.getPharm(sido, sigungu, qn);
				JSONArray jsonArray = new JSONArray(list);
				PrintWriter out = response.getWriter();
				
				if(list == null) {
					out.println("");
				} else {
					out.println(jsonArray);
				}
//				System.out.println("성공");
				
			} catch (Exception e) {
				e.printStackTrace();
//				System.out.println("실패");
			}
	
			
		} else if(command.equals("command=hospitalpaging")) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		doGet(request, response);
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
