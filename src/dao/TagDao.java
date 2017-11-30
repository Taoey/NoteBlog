package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import utils.JDBCUtil;

public class TagDao {

	private static Connection connection=null;
	private static Statement statement=null;
	private static PreparedStatement preparedStatement=null;
	private static ResultSet resultSet=null;
	
	
	
	/**
	 * 向tag表添加一条记录
	 * @param noteMetadata
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void addTag(String guid,String name) throws FileNotFoundException, IOException{
		try {
			connection=JDBCUtil.getConnection();
			String sql="insert into tag(_guid,_name) values(?,?)";
			
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,guid);
			preparedStatement.setString(2, name);		

			
			preparedStatement.execute();		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(resultSet, preparedStatement, connection);
			
		}
		
	}
	
	/**
	 * 
	 * @param tagGuid
	 * @return 1:有该标签  0:无该标签
	 * @throws Exception
	 */
	public static int isExist(String tagGuid) throws Exception{
		
			connection=JDBCUtil.getConnection();
			String sql = "select * from tag where _guid=\""+ tagGuid+"\";";//注意容易写错
			
			preparedStatement=connection.prepareStatement(sql);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				return 1;
			}
			else {
				return 0;
			}
			

	}
}
