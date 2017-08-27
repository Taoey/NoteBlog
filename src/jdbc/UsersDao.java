package jdbc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javabean.User;
import utils.JDBCUtil;

public class UsersDao {
	
	private static Connection connection=null;
	private static PreparedStatement preparedStatement=null;
	private static ResultSet resultSet=null;
	
	/**
	 * 添加一个用户
	 * @param user
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void addUser(User user) throws FileNotFoundException, IOException{
		
		try {
			connection=JDBCUtil.getConnection();
			String sql="insert into users(_name,_email,_token,_storeUrl) values(?,?,?,?)";		
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getToken());
			preparedStatement.setString(4, user.getStoreUrl());
			preparedStatement.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(resultSet, preparedStatement, connection);			
		}

	}

}
