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
		//Get Connection
		Connection dbCon = conMaker.makeConnection();
		//Prepare Statement. Then execute that statement.
		PreparedStatement ps = dbCon.prepareStatement("insert into board(email, pwd, content, createdDate) values (?,?,?,now())");
		ps.setString(1, board.getEmail());
		ps.setString(2, board.getPwd());
		ps.setString(3, board.getContent());
		ps.execute();
		//Close objects.
		ps.close();
		dbCon.close();
	}
	
	public ArrayList<BoardDTO> getAll() throws SQLException, ClassNotFoundException
	{
		//Initialize list for result.
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		//Get Connection
		Connection dbCon = conMaker.makeConnection();
		//Prepare Statement.
		PreparedStatement ps = dbCon.prepareStatement("select * from board");
		//Execute that statement. Then Get result set
		ResultSet rs = ps.executeQuery();
		//Save the result to list.
		while(rs.next())
		{
			boardList.add(new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), Timestamp.valueOf(rs.getString(5))));
		}
		//Close objects.
		rs.close();
		ps.close();
		dbCon.close();
		return boardList;
	}
}
