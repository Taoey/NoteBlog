/**
 * 涉及到多表查询
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javabean.Note;
import javabean.Note2Tag;
import javabean.Tag;
import utils.JDBCUtil;

public class MultiDao {
	private static Connection connection=null;
	private static Statement statement=null;
	private static PreparedStatement preparedStatement=null;
	private static ResultSet resultSet=null;
	
	
	/**
	 * 通过标签查找所有相关笔记
	 * @param tagGuid
	 * @return
	 * @throws Exception
	 */
	public static List<Note> getAllNoteByTag(String tagGuid) throws Exception{
		List<Note>  myNoteList=new ArrayList<Note>();
		
		connection=JDBCUtil.getConnection();
		
		String sql=String.format("SELECT * FROM note WHERE _guid IN(SELECT _noteGuid FROM note2tag WHERE _tagGuid='%s')", tagGuid);
		statement=connection.createStatement();	
		
		resultSet=statement.executeQuery(sql);
		
		while (resultSet != null && resultSet.next()) {
			
			String title = resultSet.getString("_title");
			String guid = resultSet.getString("_guid");					
			myNoteList.add(new Note(title,guid));
		}
		JDBCUtil.close(resultSet, preparedStatement, connection);
		
		return myNoteList;
		
		
		
	}
	/**
	 * 获得一个笔记的所有的标签
	 * @param noteGuid
	 * @return
	 * @throws Exception
	 */
	public static List<Tag> getAllTagByNote(String noteGuid)throws Exception{
		List<Tag>  TagList=new ArrayList<Tag>();
		
		connection=JDBCUtil.getConnection();
		
		String sql=String.format("SELECT * FROM tag WHERE _guid IN(SELECT _tagGuid FROM note2tag WHERE _noteGuid='%s')", noteGuid);
		statement=connection.createStatement();	
		
		resultSet=statement.executeQuery(sql);
		
		while (resultSet != null && resultSet.next()) {
			
			String guid = resultSet.getString("_guid");
			String name = resultSet.getString("_name");					
			TagList.add(new Tag(guid,name));
		}
		JDBCUtil.close(resultSet, preparedStatement, connection);
		return TagList;
	}
}
