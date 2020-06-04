package com.med.file.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.med.file.biz.FileBiz;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FileController
 */
@WebServlet("/xmlupload.do")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public FileController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String folder = "../semi5/xml/";
		String fileName = "";
		int maxSize = 1024*1024*15;			//15mb로 파일 크기 제한
		String encType = "UTF-8";
		String savefile = "xml";		//img라는 폴더에 저장
		ServletContext scontext = getServletContext();
		folder = scontext.getRealPath(savefile);
		System.out.println(folder);
		
		File uploadDir = new File(folder);
		
		//mkdir(): semi5라는 폴더가 없을 경우 오류남
		//mkdirs(): semi5라는 폴더가 없을 경우 semi5를 만들고 그 밑에 xml 폴더를 만듦
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
	
		try {
			MultipartRequest multi = new MultipartRequest(request, folder, maxSize, encType, new DefaultFileRenamePolicy());

			Enumeration<?> files = multi.getFileNames();		//업로드된 파일의 이름을 반환
			String file = (String)files.nextElement();
			
			fileName = multi.getFilesystemName(file);
			System.out.println(fileName);
			
			String command = multi.getParameter("command");
			System.out.println("<command : " + command + ">");
			
			String fullPath = request.getRequestURL().toString();

			String[] path = fullPath.split("xmlupload.do");
			
			String toJSON = path[0] + "xml/" + URLEncoder.encode(fileName, "UTF-8");
			System.out.println("xml 파일 경로는 : " + toJSON);
			
			FileBiz biz = new FileBiz();
			Map<String, String> map = biz.getXML(toJSON);
			
			///////////////////////////command
			//참고: 정밀(type3) 검사는 데이터가 없어서 임의로 식전혈당(checkup_beforesugar)만 추가함
			if (command.equals("insertXml_type1")) {

				if (map.isEmpty()) {
					String msg = "XML 파일 업로드 실패";
					request.setAttribute("checkup_msg", msg);
					
				} else {
					String msg = "XML 파일 업로드 성공! 값을 확인해 주세요";
					request.setAttribute("checkup_msg", msg);
					
					request.setAttribute("checkup_height", map.get("CHECKUP_HEIGHT"));
					request.setAttribute("checkup_weight", map.get("CHECKUP_WEIGHT"));
					request.setAttribute("checkup_leye", map.get("CHECKUP_LEYE"));
					request.setAttribute("checkup_reye", map.get("CHECKUP_REYE"));
					request.setAttribute("checkup_lhear", map.get("CHECKUP_LHEAR"));
					request.setAttribute("checkup_rhear", map.get("CHECKUP_RHEAR"));
					request.setAttribute("checkup_minblood", map.get("CHECKUP_MINBLOOD"));
					request.setAttribute("checkup_maxblood", map.get("CHECKUP_MAXBLOOD"));
					request.setAttribute("checkup_kidney", map.get("CHECKUP_KIDNEY"));
					request.setAttribute("checkup_hemo", map.get("CHECKUP_HEMO"));
					request.setAttribute("checkup_beforesugar", map.get("CHECKUP_BEFORESUGAR"));
					
					String checkup_type = "1";		//기본 검사
					request.setAttribute("checkup_type", checkup_type);
					
					dispatch("views/check/checkinsertform.jsp", request, response);
				}
				
			} else if (command.equals("insertXml_type2")) {
				if (map.isEmpty()) {
					String msg = "XML 파일 업로드 실패";
					request.setAttribute("checkup_msg", msg);
					
				} else {
					String msg = "XML 파일 업로드 성공! 값을 확인해 주세요";
					request.setAttribute("checkup_msg", msg);
					request.setAttribute("checkup_height", map.get("CHECKUP_HEIGHT"));
					request.setAttribute("checkup_weight", map.get("CHECKUP_WEIGHT"));
					request.setAttribute("checkup_minblood", map.get("CHECKUP_MINBLOOD"));
					request.setAttribute("checkup_maxblood", map.get("CHECKUP_MAXBLOOD"));
					request.setAttribute("checkup_kidney", map.get("CHECKUP_KIDNEY"));
					
					String checkup_type = "2";
					request.setAttribute("checkup_type", checkup_type);
					
					dispatch("views/check/checkinsertform.jsp", request, response);
				}
				
			} else if (command.equals("insertXml_type3")) {
				if (map.isEmpty()) {
					String msg = "XML 파일 업로드 실패";
					request.setAttribute("checkup_msg", msg);
					
				} else {
					String msg = "XML 파일 업로드 성공! 값을 확인해 주세요";
					request.setAttribute("checkup_msg", msg);
					request.setAttribute("checkup_beforesugar", map.get("CHECKUP_BEFORESUGAR"));
					
					String checkup_type = "3";
					request.setAttribute("checkup_type", checkup_type);
					
					dispatch("views/check/checkinsertform.jsp", request, response);
				}
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
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

}
