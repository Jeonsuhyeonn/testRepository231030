package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;

@WebServlet("/idCheck.me")
public class MemberIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberIdCheckController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String checkId = request.getParameter("checkId");
		//System.out.println(checkId);
		
		//해당 아이디값을 데이터베이스에서 있는지 없는지 조회 처리
		int count = new MemberService().checkId(checkId);   //해당 아이디가 존재하는지 숫자를 세어 있다면 사용못하게, 없다면 사용가능하게
		//count를 써서 1이면 있는거, 0이면 없는거
		
		if(count>0) {  //중복이라면 사용불가
			response.getWriter().print("NNNNN");
		} else {  //중복아니니 사용가능
			response.getWriter().print("NNNNY");
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
