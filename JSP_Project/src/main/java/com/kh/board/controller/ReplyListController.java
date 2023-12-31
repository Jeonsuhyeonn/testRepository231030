package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Reply;

@WebServlet("/replyList.bo")
public class ReplyListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReplyListController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		ArrayList<Reply> rlist = new BoardService().selectReplyList(bno);
		
		for (Reply r : rlist) {
			System.out.println(r);
		}
		
		//GSON을 이용해서 간단하게 전달해보기
		response.setContentType("application/json; charset=UTF-8");
		
		new Gson().toJson(rlist, response.getWriter());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
