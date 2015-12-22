package bc.model;

import org.junit.Test;

import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.not;

public class TestClass
{
	@Test
	public void testAddAndGet()
	{
		ConnectionMaker conMaker = new ConMakerClass();
		BoardDAO dao = new BoardDAO(conMaker);
		
		BoardDTO board1 = new BoardDTO(0, "dktfr@naver.com", "111", "Content??", null);
		BoardDTO board2 = new BoardDTO(0, "dk@gmail.com", "111", "Content!!!!!", null);
		BoardDTO board3 = new BoardDTO(0, "qwerty@naver.com", "111", "Content11111", null);
		
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
}
