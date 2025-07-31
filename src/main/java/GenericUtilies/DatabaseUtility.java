package GenericUtilies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	public Connection con;

	/**
	 * This method is used to fetch data from database
	 * 
	 * @param url
	 * @param un
	 * @param pswd
	 * @param query
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet FetchDataFromDatabase(String url, String un, String pswd, String query) throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(url, un, pswd);
		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery(query);
		return result;
	}

	/**
	 * This method is used to fetch data from database
	 * 
	 * @param query
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet FetchDataFromDatabase(String query) throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery(query);
		return result;
	}

	/**
	 * This method is used to write back data to database
	 * 
	 * @param query
	 * @return int
	 * @throws Exception
	 */
	public int writeBackDataToDatabase(String query) throws Exception {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
		Statement stat = con.createStatement();
		int result = stat.executeUpdate(query);
		return result;

	}

	/**
	 * This method is used to close database connection
	 * 
	 * @throws SQLException
	 */
	public void closeDatabaseConnection() throws SQLException {
		con.close();
	}
	
	/**
	 * This method is used to get the connection with Database
	 * @return
	 * @throws Exception
	 */
	public Connection getDBConnection() throws Exception
	{
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
		return con;
	}

}
