package library.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.action.BaseAction;
import library.action.util.ActionManager;
import library.bean.Book;
import library.dao.BookDao;
import library.dao.db.BookDaoMySQLImpl;
import library.dao.file.simple.BookDaoSimpleFileImpl;

/**
 * Servlet implementation class SimpleServletController
 */
public class SimpleServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private BookDao dao = new BookDaoSimpleFileImpl();
	private BookDao dao = new BookDaoMySQLImpl();
	
	
    public SimpleServletController() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//response.getWriter().append("List of books with author details extracted from database:" + "\n");
		//List<Book> books = dao.readAll();
		//response.getWriter().append(books.toString());
		
		String userAction = request.getParameter("action");
		int value = Integer.parseInt(userAction);
		
		BaseAction action = ActionManager.defineAction(value);
		action.doSmth(request, response);
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

            doGet(request, response);
		
	}

}
