package jdbc;
/**
 * 操作记录：
 * 20170116-0950：修改获取到的note的URL访问方式（绝对->相对），放弃domain方式（数据库数据：http://hwtblog.cn/blogs/65c16975-2784-4d9d-9257-7fd238443eab  到/blogs/65c16975-2784-4d9d-9257-7fd238443eab ）
 * 20171017-1512：添加模糊查询功能，为实现主页的搜索框功能
 * 
 * 
 */
import java.io.File;
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

import javabean.MyNote;
import utils.JDBCUtil;
import utils.Myutils;

public class NotesDao {

	private static Connection connection=null;
	private static Statement statement=null;
	private static PreparedStatement preparedStatement=null;
	private static ResultSet resultSet=null;
	
	private static String domain=null;
	/**
	 * 向数据库添加一条笔记记录
	 * @param noteMetadata
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void addNote(NoteMetadata noteMetadata,int isHave) throws FileNotFoundException, IOException{
		try {
			domain=Myutils.getProperty("domain");			
			File file=new File("");
			connection=JDBCUtil.getConnection();
			String sql="insert into notes(_title,_guid,_tagsGuid,_isUpdated,_url,_isHave) values(?,?,?,?,?,?)";
			String url=file.separator+"blogs"+file.separator+noteMetadata.getGuid();
			String tags="";
			
			if(noteMetadata.isSetTagGuids()){
				tags=noteMetadata.getTagGuids().toString().replace("[", "").replace("]", "");
			}
			
			
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,noteMetadata.getTitle() );
			preparedStatement.setString(2, noteMetadata.getGuid());			
			preparedStatement.setString(3, tags);
			preparedStatement.setLong(4, noteMetadata.getUpdated());
			preparedStatement.setString(5, url);
			preparedStatement.setInt(6,isHave);
			preparedStatement.execute();
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(resultSet, preparedStatement, connection);
			
		}
		
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
		String sql="update notes set _title=?,_tagsGuid=?,_isUpdated=?,_isHave=? where _guid= ?";
		preparedStatement=connection.prepareStatement(sql);
		
		String tags="";
		
		if(noteMetadata.isSetTagGuids()){
			tags=noteMetadata.getTagGuids().toString().replace("[", "").replace("]", "");
		}
		preparedStatement.setString(1,noteMetadata.getTitle() );
		preparedStatement.setString(2, tags);
		preparedStatement.setLong(3, noteMetadata.getUpdated());
		preparedStatement.setInt(4,isHave);
		preparedStatement.setString(5,noteMetadata.getGuid());
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
		String sql="update notes set _isHave=? where _guid= ?;";
		preparedStatement=connection.prepareStatement(sql);
		

		preparedStatement.setInt(1,isHave );
		preparedStatement.setString(2,noteMetadata.getGuid());
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
		String sql="update notes set _isHave=?";
		preparedStatement=connection.prepareStatement(sql);
		
		preparedStatement.setInt(1,isHave);
		preparedStatement.execute();
		
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
	 
	/**
	 * 查询是否某条笔记（这个方法已经被getSqlNoteCondition()方法代替 ）
	 * @param noteGuid
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static boolean isHaveNote(NoteMetadata noteMetadata) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		connection = JDBCUtil.getConnection();
		String sql = "select * from notes where _guid=\""+ noteMetadata.getGuid()+"\";";//注意容易写错
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		if (resultSet.next()) {// 存在
			JDBCUtil.close(resultSet, statement, connection);			
			return true;
		} else {
			JDBCUtil.close(resultSet, statement, connection);
			return false;
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
		String sql = "select * from notes where _guid=\""+ noteMetadata.getGuid()+"\";";//注意容易写错
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
	 * 在数据库中查询云端不存在的笔记
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static List<String> getNoHaveNotes() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
		connection = JDBCUtil.getConnection();
		String sql="select * from notes where _isHave=0";
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
		String sql="delete from notes where _guid=?";
		preparedStatement=connection.prepareStatement(sql);
		
		preparedStatement.setString(1, noteGuid);
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
	public static List<MyNote> getAllNotes() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
		List<MyNote>  myNoteList=new ArrayList<MyNote>();
		
		connection=JDBCUtil.getConnection();
		
		String sql="select * from notes";
		statement=connection.createStatement();		
		resultSet = statement.executeQuery(sql);
		while (resultSet != null && resultSet.next()) {
			
			String title = resultSet.getString("_title");
			String guid = resultSet.getString("_guid");
			String tagsGuid = resultSet.getString("_tagsGuid");
			String url = resultSet.getString("_url");
			myNoteList.add(new MyNote(title,guid,tagsGuid,url));
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
	public static List<MyNote> getNotes(String searchStr) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException{
		List<MyNote>  myNoteList=new ArrayList<MyNote>();
		
		connection=JDBCUtil.getConnection();
		
		String sql="select * from notes where _title like '%"+searchStr+"%'";
		statement=connection.createStatement();	
		
		resultSet=statement.executeQuery(sql);
		
		while (resultSet != null && resultSet.next()) {
			
			String title = resultSet.getString("_title");
			String guid = resultSet.getString("_guid");
			String tagsGuid = resultSet.getString("_tagsGuid");
			String url = resultSet.getString("_url");
			myNoteList.add(new MyNote(title,guid,tagsGuid,url));
		}
		JDBCUtil.close(resultSet, preparedStatement, connection);
		
		return myNoteList;
			

	}
	
	
	
	
	
	
	//	public static void main(String[] args) throws Exception {
//		Operate op=new Operate();
//		String bookGuid=op.getNoteBookGuid("test");
//		List<NoteMetadata> noteList=op.getNoteMetadata(bookGuid);
//		for (NoteMetadata noteMetadata : noteList) {
//			int res=getSqlNoteCondition(noteMetadata);
//			System.out.println(res);
//			
//			if(res==-1){
//				addNote(noteMetadata);
//				//保存资源
//			}
//			else if(res==1){
//				//删除原来的资源重新获取资源
//			}
//			else{
//				//不做操作
//			}
//			
//			
//		}
//		
//	}
}
