package bc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConMakerClass implements ConnectionMaker
{
	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException
	{
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost/basecamp_db", "study", "study");
	}

}
