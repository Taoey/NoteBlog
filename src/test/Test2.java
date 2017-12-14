package test;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import org.junit.Test;

public class Test2 {

	/**
	 * 向数据库中插入当前时间测试
	 * @throws Exception
	 */
	@Test
	public void insertCurrentTime() throws Exception{
		String driver="com.mysql.jdbc.Driver";
		String dbName="jdbc:mysql:///test";
		String user="root";
		String passwd="12345678";
		Class.forName(driver);
		
		String sql="insert into datetest(_dateTime)values(?)";		
		PreparedStatement ps=DriverManager.getConnection(dbName,user,passwd).prepareStatement(sql);
		
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		//System.out.println(timeStamp);
		
		ps.setTimestamp(1, timeStamp);		
		ps.execute();		
	}
}
