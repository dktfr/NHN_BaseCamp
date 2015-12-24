package bc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import bc.model.BoardDAO;
import bc.model.BoardDTO;
import bc.model.ConMakerClass;
import bc.model.ConnectionMaker;

public class ControllerServlet implements Servlet
{
	BoardDAO dao;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Init");
		
		ConnectionMaker conMaker = new ConMakerClass();
		dao = new BoardDAO(conMaker);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
		//distribute each request by using parameter named 'action'
		String action = request.getParameter("action");
		if(action.equals("list"))
		{
			try
			{
				ArrayList<BoardDTO> list = dao.getAll();
				
				RequestDispatcher rd = request.getRequestDispatcher("./board_list.jsp");
				request.setAttribute("list", list);
				rd.include(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(action.equals("add"))
		{
			//Get Parameter from add page. Then, create dto object.
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			String content = request.getParameter("content");
			BoardDTO newBoard = new BoardDTO(0, email, pwd, content, null);
			
			try {
				//Add action via DAO
				dao.add(newBoard);
				
				//Recursive Request itself because of list
				RequestDispatcher rd = request.getRequestDispatcher("./ControllerServlet?action=list");
				rd.forward(request, response);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(action.equals("modifing"))
		{
			try {
				//Get the data
				int num = Integer.parseInt(request.getParameter("num"));
				BoardDTO board = dao.get(num);
				
				//Save in attribute. Then call modify page.
				RequestDispatcher rd = request.getRequestDispatcher("./board_modify.jsp");
				request.setAttribute("data", board);
				rd.forward(request, response);
			}
			catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
}
