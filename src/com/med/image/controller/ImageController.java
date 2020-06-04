package com.med.image.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.med.image.biz.ImageBiz;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/imageupload.do")
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public ImageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String folder = "../semi5/img/";
		String fileName = "";
		int maxSize = 1024*1024*15;			//15mb로 파일 크기 제한
		String encType = "UTF-8";
		String savefile = "img";		//img라는 폴더에 저장
		ServletContext scontext = getServletContext();
		folder = scontext.getRealPath(savefile);
		
		File uploadDir = new File(folder);
		
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		try {
			MultipartRequest multi = new MultipartRequest(request, folder, maxSize, encType, new DefaultFileRenamePolicy());
			
			String command = multi.getParameter("command");
				
			System.out.println("<command : " + command + ">");
			
			Enumeration<?> files = multi.getFileNames();
			String file = (String)files.nextElement();
			
			fileName = multi.getFilesystemName(file);
			System.out.println(fileName);

			String fullpath = folder + "\\" + fileName;
			System.out.println(fullpath);

			if (command.equals("insertImage_type1")) {
				ImageBiz biz = new ImageBiz();
				String[] array = biz.getImage(fullpath);
				
				for (int i = 0; i < array.length ; i++) {
					System.out.println(i + "번째 배열: " + array[i]);
				}
				/*
				System.out.println("주민등록번호 앞자리: " + array[40]);
				System.out.println("키: " + array[91]);
				System.out.println("몸무게: " + array[95]);
				System.out.println("시력 좌: " + array[147]);
				System.out.println("시력 우: " + array[149]);
				
				System.out.println("청력 좌: " + array[150]);
				System.out.println("청력 우: " + array[152]);
				System.out.println("혈압: " + array[153]);
				
				System.out.println("요단백(양성/음성): " + array[188]);
				System.out.println("혈색소(빈혈 등): " + array[224]);
				System.out.println("당뇨질환(식전혈당): " + array[241]);
				*/

				request.setAttribute("checkup_regist", array[40]);
				request.setAttribute("checkup_height", array[91]);
				request.setAttribute("checkup_weight", array[95]);
				request.setAttribute("checkup_leye", array[147]);
				request.setAttribute("checkup_reye", array[149]);
				request.setAttribute("checkup_lhear", array[150]);
				request.setAttribute("checkup_rhear", array[152]);
				
				String[] minblood = array[153].split("~");
				System.out.println(Arrays.toString(minblood));
				
				request.setAttribute("checkup_minblood", minblood[0]);
				
				String[] maxblood = minblood[1].split("/");
				System.out.println(Arrays.toString(maxblood));
				
				request.setAttribute("checkup_maxblood", maxblood[0]);
				
				/*
				String[] kidney = array[188].split(" ");
				System.out.println(Arrays.toString(kidney));
				*/
				
				request.setAttribute("checkup_kidney", array[188]);
				
				request.setAttribute("checkup_hemo", array[224]);
				request.setAttribute("checkup_beforesugar", array[241]);
				
				String checkup_type = "1";		//기본 검사
				request.setAttribute("checkup_type", checkup_type);
				
				String msg = "이미지 등록 성공! 값을 확인해 주세요";
				request.setAttribute("checkup_msg", msg);
				
				dispatch("views/check/checkinsertform.jsp", request, response);
				
			} else if (command.equals("insertImage_type2")) {
				ImageBiz biz = new ImageBiz();
				String[] array = biz.getImage(fullpath);
				for (int i = 0; i < array.length ; i++) {
					System.out.println(i + "번째 배열: " + array[i]);
				}

				request.setAttribute("checkup_regist", array[40]);
				request.setAttribute("checkup_height", array[91]);
				request.setAttribute("checkup_weight", array[95]);
				
				String[] minblood = array[153].split("~");
				System.out.println(Arrays.toString(minblood));
				
				request.setAttribute("checkup_minblood", minblood[0]);
				
				String[] maxblood = minblood[1].split("/");
				System.out.println(Arrays.toString(maxblood));
				
				request.setAttribute("checkup_maxblood", maxblood[0]);
				
				request.setAttribute("checkup_kidney", array[188]);
				
				String checkup_type = "2";
				request.setAttribute("checkup_type", checkup_type);
				
				String msg = "이미지 등록 성공! 값을 확인해 주세요";
				request.setAttribute("checkup_msg", msg);
				
				dispatch("views/check/checkinsertform.jsp", request, response);
				
			} else if (command.equals("insertImage_type3")) {
				ImageBiz biz = new ImageBiz();
				String[] array = biz.getImage(fullpath);
				for (int i = 0; i < array.length ; i++) {
					System.out.println(i + "번째 배열: " + array[i]);
				}

				request.setAttribute("checkup_fundo", array[289]);
				request.setAttribute("checkup_beforesugar", array[277]);
				request.setAttribute("checkup_aftersugar", array[279]);
				
				String checkup_type = "3";
				request.setAttribute("checkup_type", checkup_type);
				
				String msg = "이미지 등록 성공! 값을 확인해 주세요";
				request.setAttribute("checkup_msg", msg);
				
				dispatch("views/check/checkinsertform.jsp", request, response);
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
