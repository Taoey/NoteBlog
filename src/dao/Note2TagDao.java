package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import utils.JDBCUtil;

public class Note2TagDao {
	
	private static Connection connection=null;
	private static Statement statement=null;
	private static PreparedStatement preparedStatement=null;
	private static ResultSet resultSet=null;
	
	/**
	 * 向note表添加一条笔记记录
	 * @param noteMetadata
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void addN2T(String noteGuid,String tagGuid ) throws FileNotFoundException, IOException{
		try {
			connection=JDBCUtil.getConnection();
			String sql="insert into note2tag(_noteGuid,_tagGuid) values(?,?)";
			
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,noteGuid);
			preparedStatement.setString(2, tagGuid);			

			
			preparedStatement.execute();		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(resultSet, preparedStatement, connection);
			
		}
		
	}
	

}
