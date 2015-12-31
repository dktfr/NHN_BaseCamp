package bc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import bc.model.BoardDAO;
import bc.model.BoardDTO;
import bc.model.ConMakerClass;
import bc.model.ConnectionMaker;

public class ControllerServlet extends GenericServlet
{
	BoardDAO dao;
	EmailValidator validator;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		System.out.println("Init");
		
		/*
		 * NOTE - When developer doesn't implement getServletConfig method, getServletContext must throw NullPointerException.
		 */
		ServletContext sc = this.getServletContext();
		ConnectionMaker conMaker = new ConMakerClass(sc);
		dao = new BoardDAO(conMaker);
		
		validator = new EmailValidator();
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
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
			
			//Valid Checking the email.
			if(!validator.validate(email))
			{
				//Invalid email. Send fail result message
				PrintWriter writer = response.getWriter();
				writer.print("fail");
				writer.close();
			}
			else
			{
				//Valid email. Process next
				BoardDTO newBoard = new BoardDTO(0, email, pwd, content, null, null);
				
				try {
					//Add action via DAO
					dao.add(newBoard);
					//Send success result message.
					PrintWriter writer = response.getWriter();
					writer.print("success");
					writer.close();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		else if(action.equals("modified"))
		{
			try
			{
				//Get the data
				String password = request.getParameter("pwd");
				String content = request.getParameter("content");
				int num = Integer.parseInt(request.getParameter("num"));
				//Get the data from db
				BoardDTO board = dao.get(num);
				
				//Check the password. If password is correct, update the content
				if(board.getPwd().equals(password))
				{
					board.setContent(content);
					dao.modify(board);
					PrintWriter writer = response.getWriter();
					writer.print("success");
					writer.close();
				}
				//Else then fail error
				else
				{
					PrintWriter writer = response.getWriter();
					writer.print("fail");
					writer.close();
				}
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
