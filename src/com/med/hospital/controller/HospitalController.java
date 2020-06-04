package com.med.hospital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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

import com.med.hospital.biz.HospitalBiz;

import sun.rmi.server.Dispatcher;


@WebServlet("/hospitalService.do")
public class HospitalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HospitalController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("<command : " + command + ">");
		
		HospitalBiz biz = new HospitalBiz();
		
		HttpSession session = request.getSession();
		
		if(command.contentEquals("hospitalList")) {
			
			dispatch("views/hospital/hospitalSearch.jsp", request, response);
		} else if(command.equals("hospitalSearch")) {
			String sido = request.getParameter("sido");
			String sigungu = request.getParameter("sigungu");
			String qd = request.getParameter("qd");
			String qn = request.getParameter("qn");
			
//			System.out.println(sido+sigungu+qd+qn);
			
			
			try {
				List<HashMap<String, String>> list = (List<HashMap<String, String>>) biz.getHospital(sido, sigungu, qd, qn);
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
	
			
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		doGet(request, response);
		
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
