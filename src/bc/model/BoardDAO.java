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
		//Prepare Statement for insert. Then execute that statement.
		PreparedStatement ps = dbCon.prepareStatement("insert into board(email, pwd, content, createdDate) values (?,?,?,now())");
		ps.setString(1, board.getEmail());
		ps.setString(2, board.getPwd());
		ps.setString(3, board.getContent());
		ps.execute();
		//Close objects.
		ps.close();
		dbCon.close();
	}
	
	public BoardDTO get(int num) throws SQLException, ClassNotFoundException
	{
		BoardDTO result = null;
		//Get Connection
		Connection dbCon = conMaker.makeConnection();
		//Prepare Statement for modified Date. Then execute that statement.
		PreparedStatement ps = dbCon.prepareStatement("select * from board where num = ?");
		ps.setInt(1, num);
		ResultSet rs = ps.executeQuery();
		//Get the modified date.
		if(rs.next())
		{
			result = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), Timestamp.valueOf(rs.getString(5)));
		}
		//Close objects.
		rs.close();
		ps.close();
		dbCon.close();
		return result;
	}
	
	public void modify(BoardDTO board) throws SQLException, ClassNotFoundException
	{
		//Get Connection
		Connection dbCon = conMaker.makeConnection();
		//Prepare Statement for modified Date. Then execute that statement.
		PreparedStatement ps = dbCon.prepareStatement("insert into modified(num, modifiedDate) values (?,now()) on duplicate key update modifiedDate = now()");
		ps.setInt(1, board.getNum());
		ps.execute();
		//First statement close
		ps.close();
		//Prepare Statement for update content.
		PreparedStatement ps1 = dbCon.prepareStatement("update board set content = ? where num = ?");
		ps1.setString(1, board.getContent());
		ps1.setInt(2, board.getNum());
		ps1.execute();
		//Second statement close & connection close
		ps1.close();
		dbCon.close();
	}
	
	public Timestamp getModifiedDate(int num) throws SQLException, ClassNotFoundException
	{
		Timestamp modifiedDate = null;
		//Get Connection
		Connection dbCon = conMaker.makeConnection();
		//Prepare Statement. Then Get result set
		PreparedStatement ps = dbCon.prepareStatement("select modifiedDate from modified where num = ?");
		ps.setInt(1, num);
		ResultSet rs = ps.executeQuery();
		//Get the modified date.
		if(rs.next())
		{
			modifiedDate = Timestamp.valueOf(rs.getString(1));
		}
		//Close objects.
		rs.close();
		ps.close();
		dbCon.close();
		return modifiedDate;
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
