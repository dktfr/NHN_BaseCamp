package bc.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bc.controller.EmailValidator;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;

public class TestClass
{
	private BoardDAO dao;
	private EmailValidator validator;
	
	private static String[] validEmails = new String[] { "mkyong@yahoo.com",
			"mkyong-100@yahoo.com", "mkyong.100@yahoo.com",
			"mkyong111@mkyong.com", "mkyong-100@mkyong.net",
			"mkyong.100@mkyong.com.au", "mkyong@1.com",
			"mkyong@gmail.com.com", "mkyong+100@gmail.com",
			"mkyong-100@yahoo-test.com",
			"dktfr@naver.com" };

	private static String[] invalidEmails = new String[] { "mkyong", "mkyong@.com.my",
			"mkyong123@gmail.a", "mkyong123@.com", "mkyong123@.com.com",
			".mkyong@mkyong.com", "mkyong()*@gmail.com", "mkyong@%*.com",
			"mkyong..2002@gmail.com", "mkyong.@gmail.com",
			"mkyong@mkyong@gmail.com", "mkyong@gmail.com.1a" };
	
	@Before
	public void initTest()
	{
		//Initialize DAO
		ConnectionMaker conMaker = new ConMakerClass();
		dao = new BoardDAO(conMaker);
		
		//Initialize EmailValidator
		validator = new EmailValidator();
	}
	
	@Test
	public void ValidEmailTest() {

		for (String temp : validEmails) {
			boolean valid = validator.validate(temp);
			System.out.println("Email is valid : " + temp + " , " + valid);
			assertTrue(valid);
		}

	}

	@Test
	public void InValidEmailTest() {

		for (String temp : invalidEmails) {
			boolean valid = validator.validate(temp);
			System.out.println("Email is valid : " + temp + " , " + valid);
			assertFalse(valid);
		}
	}
	
	//@Test
	public void testAddAndGet()
	{
		BoardDTO board1 = new BoardDTO(0, "dktfr@naver.com", "111", "Content??", null, null);
		BoardDTO board2 = new BoardDTO(0, "dk@gmail.com", "111", "Content!!!!!", null, null);
		BoardDTO board3 = new BoardDTO(0, "qwerty@naver.com", "111", "Content11111", null, null);
		
		try {
			dao.add(board1);
			dao.add(board2);
			dao.add(board3);
			
			ArrayList<BoardDTO> boardList = dao.getAll();
			
			assertThat(boardList.size(), not(0));
			
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
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGet()
	{
		try {
			BoardDTO b1 = dao.get(5);
			
			assertThat(b1, notNullValue());
			System.out.println(b1.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModifyAndGetModified()
	{
		BoardDTO board1 = new BoardDTO(0, "dktfr@naver.com", "111", "C1", null, null);
		
		board1.setNum(1);
		
		try {
			dao.modify(board1);
			assertThat(dao.getModifiedDate(board1.getNum()), notNullValue());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
