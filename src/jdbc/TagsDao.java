package jdbc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TagsDao {

	private static Connection connection=null;
	private static Statement statement=null;
	private static PreparedStatement preparedStatement=null;
	private static ResultSet resultSet=null;
	
	/**
	 * 为一个笔记添加一个标签
	 * @param NoteGuid
	 * @param tag
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void addTag(String NoteGuid,String tag ) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		connection=utils.JDBCUtil.getConnection();
		String sql="insert into tags(_nGuid,_tag) values(?,?)";
		preparedStatement=connection.prepareStatement(sql);
		
		preparedStatement.setString(1, NoteGuid);
		preparedStatement.setString(2, tag);
		preparedStatement.execute();
		utils.JDBCUtil.close(resultSet, preparedStatement, connection);
		
	}
	/**
	 * 为一条笔记添加多个标签
	 * @param NoteGuid
	 * @param tags
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void addTags(String NoteGuid,List<String> tags) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		connection=utils.JDBCUtil.getConnection();
		String sql="insert into tags(_nGuid,_tag) values(?,?)";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, NoteGuid);
		for(int i=0;i<tags.size();i++) {
			preparedStatement.setString(2, tags.get(i));
			preparedStatement.execute();
		}			
		utils.JDBCUtil.close(resultSet, preparedStatement, connection);
	}
}
