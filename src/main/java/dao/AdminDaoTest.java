package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

public class AdminDaoTest {

	@Test
	public void valiNamepsw() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		boolean a=AdminDao.valiNamepsw("1", "1");
		System.out.println(a);
	}
}
