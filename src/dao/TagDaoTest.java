package dao;

import org.junit.Test;

public class TagDaoTest {

	@Test
	public void addTag() throws Exception {
		TagDao.addTag("540e9199-bfb9-4208-90ea-ffc62d171b28", "涛");
	}
	
	@Test
	public void isExist() throws Exception{
		int a = TagDao.isExist("540e9199-bfb9-4208-90ea-ffc62d171b28");
		System.out.println(a);
	}
}
