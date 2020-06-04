package com.med.question.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.med.question.biz.QuestionBiz;
import com.med.question.biz.QuestionBizImpl;
import com.med.question.dto.QuestionDto;

/**
 * Servlet implementation class QuestionController
 */
@WebServlet("/question.do")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public QuestionController() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("<command : " + command + ">");
		
		HttpSession session = request.getSession();
		QuestionBiz biz = new QuestionBizImpl();

		if(command.equals("list")) {
			String searchOption = request.getParameter("option");
			String searchKeyword = request.getParameter("search");
			int pageNum = Integer.parseInt(request.getParameter("page"));
			request.setAttribute("page", pageNum);
			
			System.out.println("searchOption : " + searchOption + " / searchKeyword : " + searchKeyword);
			
			if (searchOption == null || searchOption == "" || searchKeyword == null || searchKeyword == "") {
				List<QuestionDto> list = biz.selectList(pageNum);
				
				request.setAttribute("list", list);
				
				int totalPage = biz.totalPage(pageNum);
				request.setAttribute("totalPage", totalPage);
				
				Map<String, Integer> makeGroupNum = biz.makeGroupNum(pageNum);
				int groupEndNum = makeGroupNum.get("groupEndNum");
				int groupStartNum = makeGroupNum.get("groupStartNum");
				int prevNum = makeGroupNum.get("prevNum");
				int nextNum = makeGroupNum.get("nextNum");
				request.setAttribute("groupStartNum", groupStartNum);
				request.setAttribute("groupEndNum", groupEndNum);
				request.setAttribute("prevNum", prevNum);
				request.setAttribute("nextNum", nextNum);
				
				request.setAttribute("option", searchOption);
				request.setAttribute("search", searchKeyword);
				
			} else {
				QuestionDto dto = new QuestionDto(searchOption, searchKeyword);
				List<QuestionDto> list = biz.selectList(pageNum, dto);
				request.setAttribute("list", list);
				
				int totalPage = biz.totalPage(pageNum, dto);
				request.setAttribute("totalPage", totalPage);
				
				Map<String, Integer> makeGroupNum = biz.makeGroupNum(pageNum, dto);
				int groupEndNum = makeGroupNum.get("groupEndNum");
				int groupStartNum = makeGroupNum.get("groupStartNum");
				int prevNum = makeGroupNum.get("prevNum");
				int nextNum = makeGroupNum.get("nextNum");

				request.setAttribute("groupStartNum", groupStartNum);
				request.setAttribute("groupEndNum", groupEndNum);
				request.setAttribute("prevNum", prevNum);
				request.setAttribute("nextNum", nextNum);
				
				request.setAttribute("option", searchOption);
				request.setAttribute("search", searchKeyword);
			}

			dispatch("views/question/questionList.jsp", request, response);

		} else if (command.equals("faq")) {
			
			dispatch("views/question/faq.jsp", request, response);
			
		} else if(command.equals("detail")) {
				
			int question_boardno = Integer.parseInt(request.getParameter("question_boardno"));
			QuestionDto dto = biz.selectOne(question_boardno);
			int res = biz.updateReadcount(question_boardno);
			request.setAttribute("dto", dto);

			dispatch("/views/question/questiondetail.jsp", request, response);
			
		}else if(command.equals("writeForm")) {
			
		  dispatch("/views/question/questionwrite.jsp", request, response);
		  
		}else if(command.equals("writeres")) {
			String question_title = request.getParameter("question_title");
			String question_content = request.getParameter("question_content");
			String member_id = request.getParameter("member_seq");

			QuestionDto dto = new QuestionDto();
			dto.setQuestion_title(question_title);
			dto.setQuestion_content(question_content);
			dto.setMember_id(member_id);
			int res = biz.insert(dto);
			
			if(res>0) {
				jsResponse("글 등록 성공","question.do?command=list&page=1",response);
			}else {
				jsResponse("글 등록 실패","question.do?command=writeform",response);
			}
			}else if (command.equals("delete")) {
				int question_boardno = Integer.parseInt(request.getParameter("question_boardno"));

				int res = biz.delete(question_boardno);

				if (res > 0) {
					jsResponse("글 삭제 성공","question.do?command=list&page=1",response);
			} else {
				   jsResponse("글 삭제 실패","question.do?command=detail&question_boardno="+question_boardno,response);
			}

			} else if (command.equals("update")) {
				int question_boardno = Integer.parseInt(request.getParameter("question_boardno"));
				
				QuestionDto dto = biz.selectOne(question_boardno);
				request.setAttribute("dto", dto);
				dispatch("/views/question/questionupdate.jsp", request, response);
				
			} else if (command.equals("updateres")) {
				String question_title = request.getParameter("question_title");
				String question_content = request.getParameter("question_content");
				int question_boardno = Integer.parseInt(request.getParameter("question_boardno"));
				QuestionDto dto = new QuestionDto();
				dto.setQuestion_title(question_title);
				dto.setQuestion_content(question_content);
				dto.setQuestion_boardno(question_boardno);

				int res = biz.update(dto);

				if (res > 0) {
					jsResponse("글 수정 성공","question.do?command=detail&question_boardno="+question_boardno,response);
			} else {
				jsResponse("글 수정 실패","question.do?command=update&question_boardno="+question_boardno,response);
			}

			} else if (command.equals("question")){
			  	int question_boardno = Integer.parseInt(request.getParameter("question_boardno"));
			  	QuestionDto dto = biz.selectOne(question_boardno);
			  	request.setAttribute("dto", dto);//attribute로 담았으면 같은걸로 가져와야함
			  	dispatch("/views/question/questionform.jsp",request,response);
			  	
			}else if(command.equals("questionres")) {
				
				int question_parentboardno = Integer.parseInt(request.getParameter("question_parentboardno"));
			    //String member_id = request.getParameter("member_id");
			    String question_title = request.getParameter("question_title");	
			    String question_content = request.getParameter("question_content");
			    QuestionDto dto = new QuestionDto();

			    dto.setQuestion_boardno(question_parentboardno);
			    //dto.setMember_id(member_id);
			    dto.setQuestion_title(question_title);
			    dto.setQuestion_content(question_content);
			    
			    int res = biz.questionProc(dto);
			    
			    if (res > 0) {
					jsResponse("답변 작성 성공","question.do?command=list&page=1", response);
			} else {
				jsResponse("답변 작성 실패","question.do?command=question&question_boardno="+question_parentboardno,response);
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
