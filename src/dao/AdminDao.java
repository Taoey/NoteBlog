package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDao {
	private static Connection connection=null;
	private static Statement statement=null;
	private static PreparedStatement preparedStatement=null;
	private static ResultSet resultSet=null;
	
	
	/**
	 * 判断是否为一个有效的用户名密码
	 * @param name
	 * @param passwd
	 * @return
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static boolean valiNamepsw(String name,String passwd) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		
		connection=utils.JDBCUtil.getConnection();
		statement=connection.createStatement();
		String sql =String.format("select * from admin where name='%s'and passwd='%s'",name,passwd);
		resultSet=statement.executeQuery(sql);
		if(!resultSet.next()) {
			return false;
		}
		else{
			return true;
		}
		
	}

}
