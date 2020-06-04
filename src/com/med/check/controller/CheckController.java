package com.med.check.controller;

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

import com.med.check.biz.CheckBiz;
import com.med.check.biz.CheckBizImpl;
import com.med.check.dto.CheckDto;

/**
 * Servlet implementation class CheckController
 */
@WebServlet("/checkcontroller.do")
public class CheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public CheckController() {}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("<command : " + command + ">");
		
		CheckBiz biz = new CheckBizImpl();

		if (command.equals("insert_type1")) {
			String checkup_type = "1";		//기본 검사
			request.setAttribute("checkup_type", checkup_type);
			dispatch("views/check/checkinsertform.jsp", request, response);
		
		} else if (command.equals("insert_type2")) {
			String checkup_type = "2";		//개인입력
			request.setAttribute("checkup_type", checkup_type);
			dispatch("views/check/checkinsertform.jsp", request, response);
			
		} else if (command.equals("insert_type3")) {
			String checkup_type = "3";		//정밀입력
			request.setAttribute("checkup_type", checkup_type);
			dispatch("views/check/checkinsertform.jsp", request, response);
			
		} else if (command.equals("type_1_insert")) {
			int member_seq = Integer.parseInt(request.getParameter("member_seq"));
			String checkup_type = request.getParameter("checkup_type");
			String checkup_year = request.getParameter("checkup_year"); //년
			String checkup_month = request.getParameter("checkup_month");//월
			String checkup_day = request.getParameter("checkup_day");//일
			String checkup_regist = request.getParameter("checkup_regist");//주민등록번호
			String checkup_height = request.getParameter("checkup_height");//신장
			String checkup_weight = request.getParameter("checkup_weight");//체중
			String checkup_leye = request.getParameter("checkup_leye");//시력좌
			String checkup_reye = request.getParameter("checkup_reye");//시력우
			String checkup_lhear = request.getParameter("checkup_lhear");//청각좌
			String checkup_rhear = request.getParameter("checkup_rhear");//청각우
			String checkup_maxblood = request.getParameter("checkup_maxblood");//혈압최고
			String checkup_minblood = request.getParameter("checkup_minblood");//혈압최저
			String checkup_kidney = request.getParameter("checkup_kidney");//요단백
			String checkup_hemo = request.getParameter("checkup_hemo");//혈색소
			String checkup_beforesugar = request.getParameter("checkup_beforesugar");//식전혈당
			
			int res = 0;
			
			CheckDto CheckDto = new CheckDto(member_seq, checkup_type, checkup_year, 
					checkup_month, checkup_day, checkup_regist, checkup_height,
					checkup_weight, checkup_leye, checkup_reye, checkup_lhear,
					checkup_rhear, checkup_maxblood, checkup_minblood,
					checkup_kidney, checkup_hemo, checkup_beforesugar);
				
			res = biz.checkBasicInsert(CheckDto);
				
			if (res > 0) {
				jsResponse("등록에 성공하였습니다.", "checkcontroller.do?command=insert_type1", response);
			} else {
				jsResponse("등록 실패", "checkcontroller.do?command=insert_type1", response);
			}
			
		} else if (command.equals("type_2_insert")) {
			int member_seq = Integer.parseInt(request.getParameter("member_seq"));
			String checkup_type = request.getParameter("checkup_type");
			String checkup_year = request.getParameter("checkup_year"); //년
			String checkup_month = request.getParameter("checkup_month");//월
			String checkup_day = request.getParameter("checkup_day");//일
			String checkup_regist = request.getParameter("checkup_regist");//주민등록번호
			String checkup_height = request.getParameter("checkup_height");//신장
			String checkup_weight = request.getParameter("checkup_weight");//체중
			String checkup_maxblood = request.getParameter("checkup_maxblood");//혈압최고
			String checkup_minblood = request.getParameter("checkup_minblood");//혈압최저
			String checkup_kidney = request.getParameter("checkup_kidney");//요단백

			int res = 0;
			
			CheckDto CheckDto = new CheckDto(member_seq, checkup_type, checkup_year, checkup_month, checkup_day,
					checkup_regist, checkup_height, checkup_weight, checkup_maxblood, checkup_minblood, checkup_kidney);
			
			res = biz.checkPersonalInsert(CheckDto);
			
			if (res > 0) {
				jsResponse("등록에 성공하였습니다.", "checkcontroller.do?command=insert_type2", response);
			} else {
				jsResponse("등록 실패", "checkcontroller.do?command=insert_type1", response);
			}
			
		} else if (command.equals("type_3_insert")) {
			int member_seq = Integer.parseInt(request.getParameter("member_seq"));
			String checkup_type = request.getParameter("checkup_type");
			String checkup_year = request.getParameter("checkup_year"); //년
			String checkup_month = request.getParameter("checkup_month");//월
			String checkup_day = request.getParameter("checkup_day");//일
			String checkup_fundo = request.getParameter("checkup_fundo");
			String checkup_beforesugar = request.getParameter("checkup_beforesugar");
			String checkup_aftersugar = request.getParameter("checkup_aftersugar");

			int res = 0;
			
			CheckDto CheckDto = new CheckDto(member_seq, checkup_type, checkup_year, 
					checkup_month, checkup_day, checkup_fundo,
					checkup_beforesugar, checkup_aftersugar);
			
			res = biz.checkDetailInsert(CheckDto);
			
			if (res > 0) {
				jsResponse("등록에 성공하였습니다.", "checkcontroller.do?command=insert_type3", response);
			} else {
				jsResponse("등록 실패", "checkcontroller.do?command=insert_type1", response);
			}
			
		} else if (command.equals("checkdetail")) {
			
			String member_id = request.getParameter("member_id");
			if(member_id == "") {
				request.setAttribute("member_id", member_id);
				dispatch("views/check/checkdetail.jsp", request, response);
			}else {
		    int member_seq = Integer.parseInt(request.getParameter("member_seq"));
			List<CheckDto> list = biz.checkDetail(member_seq);
			List<CheckDto> list2 = biz.checkDetail2(member_seq);
			System.out.println(list2.size());
			System.out.println(list);
			System.out.println(list2);
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			request.setAttribute("member_id", member_id);
			dispatch("views/check/checkdetail.jsp", request, response);
			}
		}
		
		/*
			
			
			int res = 0;
			
			if (checkup_type.equals("1")) {
				CheckDto CheckDto = new CheckDto(member_seq, checkup_type, checkup_year, 
						checkup_month, checkup_day, checkup_regist, checkup_height,
						checkup_weight, checkup_leye, checkup_reye, checkup_lhear,
						checkup_rhear, checkup_maxblood, checkup_minblood,
						checkup_kidney, checkup_hemo, checkup_beforesugar);
				
				res = biz.checkBasicInsert(CheckDto);
				
				if (res > 0) {
					jsResponse("등록에 성공하였습니다.", "logincontroller.do?command=main", response);
				} else {
					jsResponse("등록 실패", "logincontroller.do?command=main", response);
				}
				
			}
			
		}*/
		
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"')");
		out.println("location.href='"+url+"'");
		out.println("</script>");
	}

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
