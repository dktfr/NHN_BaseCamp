package bc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.GenericServlet;

public class ConMakerClass implements ConnectionMaker
{
	private String driver;
	private String driverUrl;
	private String userName;
	private String password;
	
	public ConMakerClass()
	{
		driver = "com.mysql.jdbc.Driver";
		driverUrl = "jdbc:mysql://localhost/basecamp_db";
		userName = "study";
		password = "study";
	}
	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException
	{
		// TODO Auto-generated method stub
		Class.forName(driver);
		return DriverManager.getConnection(driverUrl, userName, password);
	}

}
