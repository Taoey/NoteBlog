package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javabean.Note;
import javabean.Tag;
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
	 * 查找一个标签是否存在
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

	/**
	 * 获取所有标签
	 * @return
	 * @throws Exception
	 */
	public static List<Tag> getAllTag()throws Exception{
		List<Tag> tagList=new ArrayList<Tag>();
		
		connection=JDBCUtil.getConnection();
		
		String sql="select * from tag";
		statement=connection.createStatement();		
		resultSet = statement.executeQuery(sql);
		while (resultSet != null && resultSet.next()) {
			
			String guid = resultSet.getString("_guid");
			String name = resultSet.getString("_name");	
			
			tagList.add(new Tag(guid,name));
		}
		JDBCUtil.close(resultSet, preparedStatement, connection);
		
		return tagList;
		
		
	}
	/**
	 * 获取顶端10条标签
	 * @return
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static List<Tag> getTopTags() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException{
		List<Tag> tagList=new ArrayList<Tag>();
		
		connection=JDBCUtil.getConnection();
		
		String sql="SELECT * FROM tag LIMIT 10";
		statement=connection.createStatement();		
		resultSet = statement.executeQuery(sql);
		while (resultSet != null && resultSet.next()) {
			
			String guid = resultSet.getString("_guid");
			String name = resultSet.getString("_name");	
			
			tagList.add(new Tag(guid,name));
		}
		JDBCUtil.close(resultSet, preparedStatement, connection);
		
		return tagList;
	}
}
