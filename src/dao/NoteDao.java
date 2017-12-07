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

import com.evernote.edam.notestore.NoteMetadata;

import javabean.Note;
import utils.JDBCUtil;

public class NoteDao {

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
	public static void addNote(NoteMetadata noteMetadata,int isHave) throws FileNotFoundException, IOException{
		try {
			connection=JDBCUtil.getConnection();
			String sql="insert into note(_guid,_title,_isUpdated,_isHave) values(?,?,?,?)";
			
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,noteMetadata.getGuid());
			preparedStatement.setString(2, noteMetadata.getTitle());			
			preparedStatement.setLong(3, noteMetadata.getUpdated());
			preparedStatement.setInt(4, isHave);
			
			preparedStatement.execute();		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(resultSet, preparedStatement, connection);
			
		}
		
	}
	
	
	/**
	 * 查询笔记的存在即更新情况
	 * @param noteMetadata
	 * @return 0 没更新， 1 更新了 ，-1 该笔记不存在
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static int getSqlNoteCondition(NoteMetadata noteMetadata) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
		connection = JDBCUtil.getConnection();
		String sql = "select * from note where _guid=\""+ noteMetadata.getGuid()+"\";";//注意容易写错
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		if(resultSet.next()){
			
			boolean result=noteMetadata.getUpdated()==resultSet.getLong("_isUpdated");
			if(result){
				return 0;//没更新
			}
			else{
				return 1;//更新了
			}
		}
		
		return -1;//没有这条笔记
		
	}
	
	
	/**
	 * 更新数据数据
	 * @param noteMetadata
	 * @param updateCount
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void updateNote(NoteMetadata noteMetadata,int isHave) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
		connection=JDBCUtil.getConnection();
		String sql="update note set _title=?,_isUpdated=?,_isHave=? where _guid= ?";
		preparedStatement=connection.prepareStatement(sql);
		

		preparedStatement.setString(1,noteMetadata.getTitle() );
		preparedStatement.setLong(2, noteMetadata.getUpdated());
		preparedStatement.setInt(3,isHave);
		preparedStatement.setString(4,noteMetadata.getGuid());
		preparedStatement.execute();		
		
		JDBCUtil.close(resultSet, preparedStatement, connection);
		
		
	}
	
	/**
	 * 只更新isHave
	 * @param noteMetadata
	 * @param isHave
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void updateIsHave(NoteMetadata noteMetadata,int isHave) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
		connection=JDBCUtil.getConnection();
		String sql="update note set _isHave=? where _guid= ?;";
		preparedStatement=connection.prepareStatement(sql);
		

		preparedStatement.setInt(1,isHave );
		preparedStatement.setString(2,noteMetadata.getGuid());
		preparedStatement.execute();
		
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}

	/**
	 * 在数据库中查询云端不存在的笔记
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static List<String> getNoHaveNotes() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
		connection = JDBCUtil.getConnection();
		String sql="select * from note where _isHave=0";
		statement=connection.createStatement();
		resultSet=statement.executeQuery(sql);
		List<String> guidList=new ArrayList();
		
		while(resultSet.next()){
			String noteGuid=resultSet.getString("_guid");
			guidList.add(noteGuid);
		}
		
		JDBCUtil.close(resultSet, statement, connection);
		
		return guidList;
		
	}

	/**
	 * 删除一条笔记
	 * @param noteGuid
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void deleteNote(String noteGuid) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
		connection=JDBCUtil.getConnection();
		String sql="delete from note where _guid=?";
		preparedStatement=connection.prepareStatement(sql);
		
		preparedStatement.setString(1, noteGuid);
		preparedStatement.execute();
		
		JDBCUtil.close(resultSet, preparedStatement, connection);
		
		
	}
	
	/**
	 * 修改所有isHave
	 * @param isHave
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static  void updateAllIsHave (int isHave) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		connection=JDBCUtil.getConnection();
		String sql="update note set _isHave=?";
		preparedStatement=connection.prepareStatement(sql);
		
		preparedStatement.setInt(1,isHave);
		preparedStatement.execute();
		
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
	 
	/**
	 * 获取所有note数据
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static List<Note> getAllNotes() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
		List<Note>  myNoteList=new ArrayList<Note>();
		
		connection=JDBCUtil.getConnection();
		
		String sql="select * from note";
		statement=connection.createStatement();		
		resultSet = statement.executeQuery(sql);
		while (resultSet != null && resultSet.next()) {
			
			String title = resultSet.getString("_title");
			String guid = resultSet.getString("_guid");	
			
			myNoteList.add(new Note(title,guid));
		}
		JDBCUtil.close(resultSet, preparedStatement, connection);
		
		return myNoteList;
	}
	
	/**
	 *根据searchStr 搜素相应笔记
	 * @param searchStr
	 * @return myNoteList 笔记列表
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static List<Note> getNotes(String searchStr) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException{
		List<Note>  myNoteList=new ArrayList<Note>();
		
		connection=JDBCUtil.getConnection();
		
		String sql="select * from note where _title like '%"+searchStr+"%'";
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
	 * 随机获取10条笔记记录
	 * @return
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static List<Note> getRandNotes() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException{
		List<Note>  myNoteList=new ArrayList<Note>();
		
		connection=JDBCUtil.getConnection();
		
		String sql="SELECT * FROM note  ORDER BY  RAND() LIMIT 10";
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
	
	
	
	
}
