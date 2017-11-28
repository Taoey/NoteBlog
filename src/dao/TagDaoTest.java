package dao;

import org.junit.Test;

public class TagDaoTest {

	@Test
	public void addTag() throws Exception {
		TagDao.addTag("1", "涛");
	}
}
