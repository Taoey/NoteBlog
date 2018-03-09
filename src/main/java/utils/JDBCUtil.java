package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	
	/**
	 * 获得数据库连接
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException{
		String driver=Myutils.getProperty("db_driver");
		String dbName=Myutils.getProperty("db_name");
		String user=Myutils.getProperty("db_user");
		String passwd=Myutils.getProperty("db_passwd");
		Class.forName(driver);
		return DriverManager.getConnection(dbName,user,passwd);
	}
	
	/**
	 * 关闭数据库连接
	 * @param resultSet
	 * @param statement
	 * @param connection
	 */
	public static void close(ResultSet resultSet,Statement statement ,Connection connection){
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				resultSet=null;
			}
		}
		
		if(statement!=null){
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				statement=null;
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection=null;
			}
		}
		
	}
	

	
	
	
	
	
	
}


