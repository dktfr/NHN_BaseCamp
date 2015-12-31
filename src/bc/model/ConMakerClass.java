package bc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;

public class ConMakerClass implements ConnectionMaker
{
	private String driver;
	private String driverUrl;
	private String userName;
	private String password;
	
	/**
	 * Constructor for test
	 */
	public ConMakerClass()
	{
		driver = "com.mysql.jdbc.Driver";
		driverUrl = "jdbc:mysql://localhost/basecamp_db";
		userName = "study";
		password = "study";
	}
	/**
	 * Constructor for init params.
	 * @param sc ServletContext with init params
	 */
	public ConMakerClass(ServletContext sc)
	{
		driver = sc.getInitParameter("driver");
		driverUrl = sc.getInitParameter("driverUrl");
		userName = sc.getInitParameter("userName");
		password = sc.getInitParameter("password");
	}
	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException
	{
		// TODO Auto-generated method stub
		Class.forName(driver);
		return DriverManager.getConnection(driverUrl, userName, password);
	}

}
