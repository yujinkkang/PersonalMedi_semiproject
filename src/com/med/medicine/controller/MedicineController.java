package com.med.medicine.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import org.json.JSONArray;
import org.json.JSONObject;

import com.med.login.dto.LoginDto;
import com.med.medicine.biz.MedicineBiz;
import com.med.medicine.dto.MedicineDto;


/**
 * Servlet implementation class MedicineController
 */
@WebServlet("/medcontroller.do")
public class MedicineController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MedicineController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("<command : " + command + ">");
		
		MedicineBiz biz = new MedicineBiz();
		
		HttpSession session = request.getSession();
		LoginDto dto = (LoginDto) session.getAttribute("loginDto");
		
		if (dto == null) {
			jsResponse("로그인을 한 회원만 이용할 수 있는 메뉴입니다", 
					"logincontroller.do?command=main", response);
			
		} else {
			int member_seq = dto.getMember_seq();
			
			if (command.equals("medicineList")) {
				System.out.println("member_seq는 : " + member_seq);
				
				//int member_seq, String mydrug_name, String mydrug_about, Date mydrug_date
				List<MedicineDto> list = biz.selectMedList(member_seq);
				request.setAttribute("list", list);

				dispatch("views/medicine/insertMedicine.jsp", request, response);
				
			} else if (command.equals("medicineSearch")) {
				String item_name = request.getParameter("item_name");
				System.out.println(item_name);
				
				try {
					List<HashMap<String, String>> list = biz.getMedicine(URLEncoder.encode(item_name, "UTF-8"));
					JSONArray jsonArray = new JSONArray(list);
					PrintWriter out = response.getWriter();
					
					if(list == null) {
						out.println("");
					} else {
						out.println(jsonArray);
					}
					
					System.out.println("성공");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("실패");
				}
				
				
			} else if (command.equals("medicineUpdate")) {
				String[] item_seqs = request.getParameterValues("item_seq");
				
				if (item_seqs == null || item_seqs.length == 0) {
					jsResponse("등록하실 약을 선택해 주세요.", "medcontroller.do?command=medicineList", response);
				} else {
					String item_seq = item_seqs[0];
					System.out.println("약품 번호는 : " + item_seq);
					try {
						Map<String, String> map = biz.getMedicineSeq(URLEncoder.encode(item_seq, "UTF-8"));
						
						//session에서 member_seq 받은 거
						System.out.println("member_seq : " + member_seq);
						
						String drug_code = map.get("ITEM_SEQ");
						String mydrug_name = map.get("ITEM_NAME");
						String mydrug_about = map.get("CLASS_NAME");
						String mydrug_image = map.get("ITEM_IMAGE");
						MedicineDto MedicineDto = new MedicineDto(member_seq, drug_code, mydrug_name, mydrug_about, mydrug_image);
						
						request.setAttribute("MedicineDto", MedicineDto);
						int res = biz.insertMedicine(MedicineDto);
						
						if (res > 0) {
							jsResponse("등록 성공", "medcontroller.do?command=medicineList", response);
						} else {
							jsResponse("등록 실패", "medcontroller.do?command=medicineList", response);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			} else if (command.equals("isDur")) {
				String item_name = request.getParameter("item_name");
				try {
					Map<String, String> map = biz.getDUR(URLEncoder.encode(item_name, "UTF-8"));
					JSONObject jsonObject;
					
					if (map.isEmpty()) {
						System.out.println("map이 null값");
						jsonObject = null;
						PrintWriter out = response.getWriter();
						out.println(jsonObject);
						
					} else {
						jsonObject = new JSONObject(map);
						System.out.println(map.toString());
						PrintWriter out = response.getWriter();
						out.println(jsonObject);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			} else if (command.equals("deleteMedicine")) {
				int mydrug_seq = Integer.parseInt(request.getParameter("seq"));
				System.out.println(mydrug_seq);
				
				int res = biz.medicineDelete(mydrug_seq);
				
				if (res > 0) {
					jsResponse("삭제 성공", "medcontroller.do?command=medicineList", response);
				} else {
					jsResponse("삭제 실패", "medcontroller.do?command=medicineList", response);
				}
				
			}
			
		}
		

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
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"')");
		out.println("location.href='"+url+"'");
		out.println("</script>");
	}
	
	

}
