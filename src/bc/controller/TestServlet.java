package bc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import bc.model.BoardDAO;
import bc.model.BoardDTO;

public class TestServlet implements Servlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Init");
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
		BoardDAO dao = new BoardDAO();
		
		BoardDTO board1 = new BoardDTO(0, "dktfr@naver.com", "111", "Content??", null);
		BoardDTO board2 = new BoardDTO(0, "dk@gmail.com", "111", "Content!!!!!", null);
		BoardDTO board3 = new BoardDTO(0, "qwerty@naver.com", "111", "Content11111", null);
		
		try {
			dao.add(board1);
			dao.add(board2);
			dao.add(board3);
			
			ArrayList<BoardDTO> boardList = dao.getAll();
			
			if(boardList.size() > 0)
			{
				for(BoardDTO b : boardList)
				{
					System.out.println(b.toString());
				}
			}
			else
			{
				System.out.println("list is empty");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
