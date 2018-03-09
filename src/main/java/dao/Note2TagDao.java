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
import utils.JDBCUtil;

public class Note2TagDao {
	
	private static Connection connection=null;
	private static Statement statement=null;
	private static PreparedStatement preparedStatement=null;
	private static ResultSet resultSet=null;
	
	/**
	 * 向Note2Tag表添加一条笔记记录
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
	
	/**
	 * 判断一条关系是否存在
	 */
	public static int isExist(String noteGuid,String tagGuid) throws Exception {

		connection = JDBCUtil.getConnection();		
		String sql = String.format("select * from note2tag where _noteGuid=\"%S\" and  _tagGuid=\"%s\";", noteGuid,tagGuid);				
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		if(resultSet.next()){
			JDBCUtil.close(resultSet, preparedStatement, connection);
			return 1;

		}
		JDBCUtil.close(resultSet, preparedStatement, connection);
		return 0;//没有这条笔记
		
	
	}

	/**
	 * 获得和NoteGuid 全部相关的关系
	 * @param _noteGuid
	 * @return
	 * @throws Exception
	 */
	public static List<String> getAllTagGuid(String _noteGuid)throws Exception{

		List<String>  tagGuidList=new ArrayList<String>();
		
		connection=JDBCUtil.getConnection();
		
		String sql="select _tagGuid from note2tag where _noteGuid=\""+_noteGuid+"\"";
		statement=connection.createStatement();		
		resultSet = statement.executeQuery(sql);
		while (resultSet != null && resultSet.next()) {
			
			String tagGuid = resultSet.getString("_tagGuid");			
			
			tagGuidList.add(tagGuid);
		}
		JDBCUtil.close(resultSet, preparedStatement, connection);
		
		return tagGuidList;
	
		
	}
	
	/**
	 * 获得和tagGuid 全部相关的关系
	 * @param _tagGuid
	 * @return
	 * @throws Exception
	 */
	public static List<String> getAllTagGuid2(String _tagGuid)throws Exception{

		List<String>  tagGuidList=new ArrayList<String>();
		
		connection=JDBCUtil.getConnection();
		
		String sql="select _noteGuid from note2tag where _tagGuid=\""+_tagGuid+"\"";
		statement=connection.createStatement();		
		resultSet = statement.executeQuery(sql);
		while (resultSet != null && resultSet.next()) {
			
			String tagGuid = resultSet.getString("_noteGuid");			
			
			tagGuidList.add(tagGuid);
		}
		JDBCUtil.close(resultSet, preparedStatement, connection);
		
		return tagGuidList;
	
		
	}
	
	
	/**
	 * 删除一条关系
	 * @param noteGuid
	 * @param tagGuid
	 * @throws Exception
	 */
	public static void delete(String noteGuid, String tagGuid) throws Exception {
		connection=JDBCUtil.getConnection();
		String sql="delete from note2tag where _noteGuid=? and _tagGuid=?";
		preparedStatement=connection.prepareStatement(sql);
		
		preparedStatement.setString(1, noteGuid);
		preparedStatement.setString(2, tagGuid);
		preparedStatement.execute();
		
		JDBCUtil.close(resultSet, preparedStatement, connection);		
		
	}
	
	/**
	 * 获取一个标签的相关笔记的数量
	 * @param tagGuid
	 * @return
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static int getNoteCount(String tagGuid) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		int noteCount=0;
		connection=JDBCUtil.getConnection();
		
		String sql=String.format("SELECT COUNT(_noteGuid) AS _noteCount FROM note2tag WHERE _tagGuid='%s'", tagGuid);
		statement=connection.createStatement();		
		resultSet = statement.executeQuery(sql);
		while (resultSet != null && resultSet.next()) {			
			noteCount = resultSet.getInt("_noteCount");				
		}
		JDBCUtil.close(resultSet, preparedStatement, connection);		
		return noteCount;
	}
	

}
