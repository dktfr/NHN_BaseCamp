package bc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class BoardDAO
{
	private ConnectionMaker conMaker;
	
	public BoardDAO(ConnectionMaker maker)
	{
		this.conMaker = maker;
	}
	
	public void add(BoardDTO board) throws SQLException, ClassNotFoundException
	{	
		Connection dbCon = conMaker.makeConnection();
		PreparedStatement ps = dbCon.prepareStatement("insert into board(email, pwd, content, createdDate) values (?,?,?,now())");
		
		ps.setString(1, board.getEmail());
		ps.setString(2, board.getPwd());
		ps.setString(3, board.getContent());
		ps.execute();
		
		ps.close();
		dbCon.close();
	}
	
	public ArrayList<BoardDTO> getAll() throws SQLException, ClassNotFoundException
	{
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		Connection dbCon = conMaker.makeConnection();
		
		PreparedStatement ps = dbCon.prepareStatement("select * from board");
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			boardList.add(new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), Timestamp.valueOf(rs.getString(5))));
		}
		
		rs.close();
		ps.close();
		dbCon.close();
		return boardList;
	}
}
