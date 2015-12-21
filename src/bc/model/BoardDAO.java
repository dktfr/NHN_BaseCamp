package bc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class BoardDAO
{
	public void add(BoardDTO board) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/basecamp_db", "study", "study");
		
		PreparedStatement ps = c.prepareStatement("insert into board(email, pwd, content, createdDate) values (?,?,?,now())");
		
		ps.setString(1, board.getEmail());
		ps.setString(2, board.getPwd());
		ps.setString(3, board.getContent());
		ps.execute();
		
		ps.close();
	}
	
	public ArrayList<BoardDTO> getAll() throws ClassNotFoundException, SQLException
	{
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/basecamp_db", "study", "study");
		
		PreparedStatement ps = c.prepareStatement("select * from board");
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			boardList.add(new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), Timestamp.valueOf(rs.getString(5))));
		}
		return boardList;
	}
}
